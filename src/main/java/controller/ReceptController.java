package controller;

import dal.ReceptDAOSQL;

public class ReceptController {

    public ReceptDAOSQL getData() {
        return new ReceptDAOSQL();
    }
}
