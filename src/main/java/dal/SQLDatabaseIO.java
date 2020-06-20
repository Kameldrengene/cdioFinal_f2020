package dal;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.sql.*;

public class SQLDatabaseIO {
    private final String DatabaseURL;
    private final String USER;
    private final String PASS;
    private String db_name = "cdioFinal_2020";
    private boolean connected = false;
    private Connection conn = null;
    private Statement stmt = null;

    public boolean isConnected() {
        return connected;
    }

    public SQLDatabaseIO(String USER, String PASSWORD, String URL, int PORT) {
        this.USER = USER;
        this.PASS = PASSWORD;
        this.DatabaseURL = "jdbc:mysql://" + URL + ":"+PORT+"/"+db_name+"?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }

    //Tells object what DB to use
    public void setDB(String db) throws SQLException {
        this.db_name = db;
        connect();
        query("use "+db+";");
        close();
    }

    //Try to connect to DB
    public void connect() throws SQLException {
        if(!connected){
            String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                throw new SQLException();
            }
            conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
            connected = true;
        }
    }

    //Runs update on mysql server.
    public void update(String query) throws SQLException {
        if(connected){
            System.out.println("starting");
            stmt = conn.createStatement();
            stmt.executeUpdate("use "+db_name);
            stmt.executeUpdate(query);
            System.out.println("executed!");
        }
    }

    //Runs query on mysql server, and returns ResultSet object.
    public ResultSet query(String query) throws SQLException {
        ResultSet result = null;
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            stmt = conn.createStatement();
            stmt.executeUpdate("use "+db_name);
            result = stmt.executeQuery(query);
        }
        return result;
    }

    //Close connection to mysql server
    public void close() throws SQLException {
        if(connected){
            conn.close();
            connected=false;
        }
    }

    public WebApplicationException buildError(Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
