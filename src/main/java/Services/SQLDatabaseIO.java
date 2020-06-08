package services;

import java.sql.*;

public class SQLDatabaseIO {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private String DatabaseURL;
    private String USER;
    private String PASS;
    private String db_name = "cdio1_2020";
    private boolean connected = false;
    private Connection conn = null;
    private Statement stmt = null;


    public SQLDatabaseIO(String USER, String PASSWORD, String URL, int PORT) {
        this.USER = USER;
        this.PASS = PASSWORD;
        this.DatabaseURL = "jdbc:mysql://" + URL + ":"+PORT+"/"+db_name+"?characterEncoding=latin1&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }
    public void setDB(String db){
        this.db_name = db;
    } //Tells object what DB to use
    //Try to connect to DB
    public void connect() {
        if(!connected){
            try {
                Class.forName(JDBC_DRIVER);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
//            System.out.println("Connecting to DB");
            try{
                conn = DriverManager.getConnection(DatabaseURL, USER, PASS);
                connected = true;
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
                stmt = conn.createStatement();
                stmt.executeUpdate("use "+db_name);
                stmt.executeUpdate(query);
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
            } catch (SQLException e) {
                connected=true;
                e.printStackTrace();
            }

        }
    }

}
