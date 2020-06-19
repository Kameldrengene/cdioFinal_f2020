package Data;



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
    public void setDB(String db){
        this.db_name = db;
        connect();
        query("use "+db+";");
        close();
    } //Tells object what DB to use

    //Try to connect to DB
    public void connect() {
        if(!connected){
            try {
                String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            System.out.println("Connecting to DB");
            try{
                conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
                connected = true;
                //System.out.println("connected");
            }catch(SQLException e){
                //System.out.println("Connecting failed");
                connected=false;
                e.printStackTrace();
            }
        }
    }
    //Runs update on mysql server.
    public void update(String query){
        if(connected){
            try {
                System.out.println("starting");
                stmt = conn.createStatement();
                stmt.executeUpdate("use "+db_name);
                stmt.executeUpdate(query);
                System.out.println("executed!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    //Runs query on mysql server, and returns ResultSet object.
    public ResultSet query(String query){
        ResultSet result = null;
        if(!connected){
            System.out.println("Connect to a DB first");
        } else{
            try {
                stmt = conn.createStatement();
                stmt.executeUpdate("use "+db_name);
                result = stmt.executeQuery(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    public void close(){
        if(connected){
            try {
                conn.close();
                connected=false;
                //System.out.println("closed");
            } catch (SQLException e) {
                connected=true;
                e.printStackTrace();
            }

        }
    }

    public WebApplicationException buildError(Status status, String msg){
        return new WebApplicationException(Response.status(status).entity(msg).build());
    }

}
