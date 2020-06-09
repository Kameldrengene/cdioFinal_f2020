package dal;

public class ReceptDAOSQL {
    SQLDatabaseIO db = new SQLDatabaseIO("kamel", "dreng", "runerne.dk", 8003); //Makes new SQLDatabaseIO object.
    db.connect();

}
