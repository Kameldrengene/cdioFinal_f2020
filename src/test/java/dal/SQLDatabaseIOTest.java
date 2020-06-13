package dal;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class SQLDatabaseIOTest {
    SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    @Test
    public void connect() {
        sqlDatabaseIO.connect();
        assertTrue(sqlDatabaseIO.isConnected());

        sqlDatabaseIO.close();

    }

    @Test
    public void close() {
        sqlDatabaseIO.close();
        assertFalse(sqlDatabaseIO.isConnected());
    }


    @Test
    public void query() {
        int expected = 10;
        int actual = 0;
        sqlDatabaseIO.connect();
        ResultSet rs = sqlDatabaseIO.query("SELECT * FROM Recepter WHERE RID=10");
        try {
            rs.next();
           actual = rs.getInt("RID");
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(expected,actual,0);
        sqlDatabaseIO.close();

    }

    @Test
    public void update() {
        int expected = 12;
        int actual = 0;

        sqlDatabaseIO.connect();
        sqlDatabaseIO.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + "12" + "','" + "Mojito" + "','" + "1" + "','" + "6.2" + "','" + "1.5" + "')");
        ResultSet rs = sqlDatabaseIO.query("SELECT * FROM Recepter WHERE RID=12");
        try {
            rs.next();
            actual = rs.getInt("RID");
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(expected,actual,0);
        sqlDatabaseIO.update("DELETE FROM cdioFinal_2020.Recepter WHERE RID = 12 AND raavareID = 1");

        sqlDatabaseIO.close();

    }

}