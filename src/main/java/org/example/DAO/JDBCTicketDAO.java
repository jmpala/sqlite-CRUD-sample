package org.example.DAO;

import org.example.model.Ticket;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class JDBCTicketDAO implements TicketDAO<Ticket> {

    private DataSource ds;

    public JDBCTicketDAO(DataSource ds) {
        this.ds = ds;
    }

    public void init() {

        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement()) {

            st.executeUpdate(CREATE_TABLE);

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }

    }

    public void add(Ticket element) {
        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(INSERT)) {

            st.setInt(1, element.getId());
            st.setString(2, element.getTitle());
            st.setString(3, element.getDescription());
            st.setString(4, element.getStatus());

            st.execute();

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void remove(Ticket element) {
        removeById(element.getId());
    }

    public void removeById(long id) {
        try (Connection conn = ds.getConnection();
             PreparedStatement st = conn.prepareStatement(DELETE_BY_ID)) {

            st.setLong(1, id);

            if (st.executeUpdate() > 0) {
                System.out.format("Element with id %d removed from %s table", id, TABLE_NAME);
            } else {
                System.out.format("Can't remove element with id %d from %s table", id, TABLE_NAME);
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
    }

    public Optional<Ticket> findById(long id) {

        try (Connection conn = ds.getConnection();
            PreparedStatement st = conn.prepareStatement(FIND_BY_ID)) {

            st.setLong(1, id);

            try (ResultSet rs = st.executeQuery()) {

                if (rs.next()) {

                    int idd = rs.getInt(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    String status = rs.getString(4);

                    return Optional.of(new Ticket(idd, title, description, status));
                } else {
                    return Optional.empty();
                }

            } catch (SQLException e) {
                System.out.println("Failed query: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.out.println("Query failed: " + e.getMessage());
        }
        return Optional.empty();
    }

    public List<Ticket> findAll() {

        try (Connection conn = ds.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(FIND_ALL)) {

            List<Ticket> tickets = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);
                tickets.add(new Ticket(id, title, description, status));
            }

            return tickets;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void removeAll() {

        try (Connection conn = ds.getConnection();
            Statement st = conn.createStatement()) {

            st.executeUpdate(CLEAR_TABLE);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void addAll(List<Ticket> elements) {
        for (Ticket t : elements) {
            add(t);
        }
    }

    public void update(Ticket element) {

        try (Connection conn = ds.getConnection();
            PreparedStatement st = conn.prepareStatement(UPDATE)) {

            Optional opt = findById(element.getId());
            if (opt.isPresent()) {
                st.setString(1, element.getTitle());
                st.setString(2, element.getDescription());
                st.setString(3, element.getStatus());
                st.setLong(4, element.getId());

                st.execute();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
