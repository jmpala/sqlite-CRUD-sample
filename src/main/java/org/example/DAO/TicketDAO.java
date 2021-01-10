package org.example.DAO;

import org.example.model.Ticket;

public interface TicketDAO<Ticket> {

    public static final String TABLE_NAME = "tickets";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_STATUS = "status";

    public static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " (" + COLUMN_ID + " INTEGER PRIMARY KEY, " + COLUMN_TITLE + " TEXT, "
            + COLUMN_DESCRIPTION + " TEXT, " + COLUMN_STATUS + " TEXT)";

    public static final String INSERT = "INSERT INTO " + TABLE_NAME
            + " (" + COLUMN_ID + ", " + COLUMN_TITLE + ", "
            + COLUMN_DESCRIPTION + ", " + COLUMN_STATUS + ") VALUES(?,?,?,?)";

    public static final String UPDATE = "UPDATE " + TABLE_NAME + " SET "
            + COLUMN_TITLE + " = ?, "
            + COLUMN_DESCRIPTION + " = ?, "
            + COLUMN_STATUS + " = ? "
            + "WHERE " + COLUMN_ID + " = ?";

    public static final String DELETE_BY_ID = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

    public static final String FIND_BY_ID = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = ?";

    public static final String CLEAR_TABLE = "DELETE FROM " + TABLE_NAME;

    public static final String FIND_ALL = "SELECT * FROM " + TABLE_NAME;
}
