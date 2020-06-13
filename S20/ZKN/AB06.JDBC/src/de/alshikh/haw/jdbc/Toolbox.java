package de.alshikh.haw.jdbc;

import oracle.sql.DATE;

import java.sql.*;

public final class Toolbox {

    private Toolbox() {}

    public static void printTable(String sql, Connection conn) {
        try {
            //System.out.println("Table: " + tableName.toUpperCase());

            try (PreparedStatement selectStmt = conn.prepareStatement(
                    sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                 ResultSet rs = selectStmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                    System.out.println("no rows found");
                } else {
                    for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                        if (rs.getMetaData().getColumnName(i).toLowerCase().equals("geburtsdatum"))
                            System.out.printf("%-30s", rs.getMetaData().getColumnName(i));
                        else
                            System.out.printf("%-15s", rs.getMetaData().getColumnName(i));
                    }
                    System.out.println();
                    while (rs.next()) {
                        for (int i = 1; i < rs.getMetaData().getColumnCount() + 1; i++) {
                            if (rs.getMetaData().getColumnName(i).toLowerCase().equals("geburtsdatum"))
                                System.out.printf("%-30s", rs.getObject(i));
                            else
                                System.out.printf("%-15s", rs.getObject(i));
                        }
                        System.out.println();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void executeSQL(Connection con, String sql) {
        try {
            con.prepareStatement(sql).executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int executeWithID(Connection con, String sql, String[] idColumn) {
        int id=0;
        try {
            PreparedStatement statement = con.prepareStatement(sql, idColumn);
            statement.executeQuery();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return id;
    }

    public static int addEhemalig(Connection con,
        String vorname, String nachname, String geburtsname,
        String geburtsdatum, String geschlecht, int telefonnummer,
        String eMail, String strasse, int strNr, int plz, String ort) {

        int id = 0;

        // Adresse
        String sql = "INSERT INTO Adresse(Strasse, StrNr, PLZ, Ort)" +
                     " VALUES ('" +strasse+ "', " +strNr+ ", " +plz+ ", '" +ort+ "')";
        id = executeWithID(con, sql, new String[]{"AdresseID"});

        // Person
        sql = "INSERT INTO Person(Vorname, Nachname, Geburtsname, Geburtsdatum, " +
                "Geschlecht, AdresseID, Telefonnummer, EMail)" +

                " VALUES ('" +vorname+ "', '" +nachname+ "', '" +geburtsname+ "', DATE '"
                +geburtsdatum+ "', '" +geschlecht+ "', " +id+ ", '" +telefonnummer+ "', '" +eMail+ "')";
        id = executeWithID(con, sql, new String[]{"PersonID"});

        // Ehemalige
        id = executeWithID(con,"INSERT INTO Ehemaligen VALUES (" +id+ ")", new String[]{"EhemaligenID"});
        return id;
    }
}
