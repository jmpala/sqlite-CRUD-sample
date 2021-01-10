package org.example;

import org.example.DAO.JDBCTicketDAO;
import org.example.model.Ticket;
import org.sqlite.SQLiteDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class App {
    public static void main(String[] args) {
        // Setting DS
        SQLiteDataSource ds = new SQLiteDataSource();
        ds.setUrl("jdbc:sqlite:ticket.db");
        JDBCTicketDAO ticketDAO = new JDBCTicketDAO(ds);

        ticketDAO.init();

        // Add 1 object to table
        Ticket ticket1 = new Ticket(1, "Title1", "Descript1", "A");
        ticketDAO.add(ticket1);

        // Add multiple objects to table
        Ticket ticket2 = new Ticket(2, "Title2", "Descript2", "A");
        Ticket ticket3 = new Ticket(3, "Title3", "Descript3", "A");
        Ticket ticket4 = new Ticket(4, "Title4", "Descript4", "A");
        Ticket ticket5 = new Ticket(5, "Title5", "Descript5", "B");

        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket2);
        tickets.add(ticket3);
        tickets.add(ticket4);
        tickets.add(ticket5);

        ticketDAO.addAll(tickets);

        // Delete element 4
        ticketDAO.remove(ticket4);

        // Delete by id
        ticketDAO.removeById(3);

        // Find by id
        Optional<Ticket> opt = ticketDAO.findById(1);
        if (opt.isPresent()) {
            System.out.println();
            System.out.println(opt.get().toString());
        }

        // Update element 2
        ticketDAO.update(new Ticket(2, "Problema", "No me anda el pc", "X"));

        // Find all
        List<Ticket> allTickets = ticketDAO.findAll();
        System.out.println(allTickets.toString());

        // Clear table
        ticketDAO.removeAll();
    }
}
