package dal;

import dal.SQLDatabaseIO;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class SQLDatabaseIOTest {
    final SQLDatabaseIO sqlDatabaseIO = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003);

    @Test
    void connect() {
        try {
            sqlDatabaseIO.setDB("cdioTest_2020");
            sqlDatabaseIO.connect();
            assertTrue(sqlDatabaseIO.isConnected());
            sqlDatabaseIO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Test
    void close() {
        try {
            sqlDatabaseIO.setDB("cdioTest_2020");
            sqlDatabaseIO.close();
            assertFalse(sqlDatabaseIO.isConnected());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Test
    void query() {
        int expected = 10;
        int actual = 0;
        try {
            sqlDatabaseIO.setDB("cdioTest_2020");
            sqlDatabaseIO.connect();
            ResultSet rs = sqlDatabaseIO.query("SELECT * FROM Recepter WHERE RID=10");
            rs.next();
            actual = rs.getInt("RID");
            assertEquals(expected,actual,0);
            sqlDatabaseIO.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Test
    void setDB() {
    }



    @Test
    void update() {
        int expected = 99;
        int actual = 0;

        try {
            sqlDatabaseIO.setDB("cdioTest_2020");
            sqlDatabaseIO.connect();
            sqlDatabaseIO.update("insert into Recepter (RID, RName, raavareID, nonNetto, Tolerance) VALUE ('" + "99" + "','" + "Mojito" + "','" + "1" + "','" + "6.2" + "','" + "1.5" + "')");
            ResultSet rs = sqlDatabaseIO.query("SELECT * FROM Recepter WHERE RID=99");
            rs.next();
            actual = rs.getInt("RID");
            assertEquals(expected,actual,0);
            sqlDatabaseIO.update("DELETE FROM cdioTest_2020.Recepter WHERE RID = 99 AND raavareID = 1");
            sqlDatabaseIO.close();
        }catch (SQLException e){
            e.printStackTrace();
        }

    }
}