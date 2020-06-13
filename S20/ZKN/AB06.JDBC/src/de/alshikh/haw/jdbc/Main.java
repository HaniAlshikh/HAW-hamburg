package de.alshikh.haw.jdbc;

import java.io.Console;
import java.sql.*;

public class Main {

    public static Connection connect(String url) throws SQLException {
        Console console = System.console();
        if (console == null) {
            System.out.println("Couldn't get Console instance");
            System.exit(1);
        }
        String user = console.readLine("User: ");
        String pass = new String(console.readPassword("Pass: "));
        return DriverManager.getConnection(url, user, pass);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // driver
        Class.forName("oracle.jdbc.driver.OracleDriver");


        // 1. Man kann eine Verbindung zur Datenbank aufbauen.
        String url = "jdbc:oracle:thin:@ora14.informatik.haw-hamburg.de:1521:inf14";
        Connection con = connect(url);

        // set save point to rollback
        con.setAutoCommit(false);
        Savepoint start = con.setSavepoint();


        // 2. Man kann neue Ehemalige in die Datenbank aufnehmen
        int ehemaligeID = Toolbox.addEhemalig(con,
                "HAW", "Informatik", "Student",
                "1994-12-25", "M", 123456789,
                null, "Feldstr", 1, 20357, "Hamburg");

        Toolbox.addEhemalig(con,
                "Jonas", "Müller", "Schneider",
                "1994-12-25", "M", 123456789,
                null, "Straße", 1, 20357, "Hamburg");



        // 3. Man kann sich alle in der Datenbank enthaltenen Ehemaligen anzeigen lassen.
        String sql = "SELECT * FROM EHEMALIGEN, PERSON P" +
                     " INNER JOIN ADRESSE A ON A.ADRESSEID = P.ADRESSEID" +
                     " WHERE EHEMALIGENID = PERSONID";
        Toolbox.printTable(sql, con);


        // 4. Man kann sich alle Ehemaligen anzeigen lassen, die an der Hochschule
        //  „HAW Hamburg“ den Studiengang „Informatik“ jemals studiert haben
        sql = "INSERT INTO Adresse(Strasse, StrNr, PLZ, Ort)" +
              " VALUES ('Berliner Tor', 5, 20099, 'Hamburg')";
        int hawAdresseID = Toolbox.executeWithID(con, sql, new String[]{"AdresseID"});

        sql = "INSERT INTO Studiengang(Bezeichnung) VALUES ('Informatik')";
        int informatikID = Toolbox.executeWithID(con, sql, new String[]{"StudiengangID"});

        sql = "INSERT INTO Lerninstitute(Typ, Bezeichnung, AdresseID)" +
              " VALUES ('Fachhochschule', 'HAW Hamburg', " +hawAdresseID+ ")";
        int hawID = Toolbox.executeWithID(con, sql, new String[]{"LerninstituteID"});

        sql = "INSERT INTO Hochschule VALUES (" +hawID+ ")";
        int hawHochschuleID = Toolbox.executeWithID(con, sql, new String[]{"HochschuleID"});

        sql = "INSERT INTO hat_besucht " +
              " VALUES (" +ehemaligeID+ ", " +informatikID+ ", " +hawHochschuleID+ ", INTERVAL '3' YEAR)";
        Toolbox.executeSQL(con, sql);

        sql = "SELECT * FROM Person" +
                " WHERE PersonID in (" +
                    " SELECT EhemaligenID FROM hat_besucht" +
                    " WHERE StudiengangID = " +informatikID+ " AND HochschuleID =" +hawHochschuleID+
                ")";
        Toolbox.printTable(sql, con);

        // roll back changes
        con.rollback(start);

        // 5. Man kann die Verbindung zur Datenbank wieder abbauen.
        con.close();
    }
}
