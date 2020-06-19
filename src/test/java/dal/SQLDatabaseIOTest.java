package dal;

import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLDatabaseIOTest {
    final SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    @Test
    void connect() {
        sqlDatabaseIO.setDB("cdioTest_2020");
        sqlDatabaseIO.connect();
        assertTrue(sqlDatabaseIO.isConnected());

        sqlDatabaseIO.close();
    }

    @Test
    void close() {
        sqlDatabaseIO.setDB("cdioTest_2020");
        sqlDatabaseIO.close();
        assertFalse(sqlDatabaseIO.isConnected());
    }
    @Test
    void query() {
        sqlDatabaseIO.setDB("cdioTest_2020");
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
    void setDB() {
    }



    @Test
    void update() {
        int expected = 99;
        int actual = 0;

        sqlDatabaseIO.setDB("cdioTest_2020");
        sqlDatabaseIO.connect();
        sqlDatabaseIO.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + "99" + "','" + "Mojito" + "','" + "1" + "','" + "6.2" + "','" + "1.5" + "')");
        ResultSet rs = sqlDatabaseIO.query("SELECT * FROM Recepter WHERE RID=99");
        try {
            rs.next();
            actual = rs.getInt("RID");
        }catch (SQLException e){
            e.printStackTrace();
        }
        assertEquals(expected,actual,0);
        sqlDatabaseIO.update("DELETE FROM cdioTest_2020.Recepter WHERE RID = 99 AND raavareID = 1");

        sqlDatabaseIO.close();
    }
}