package Data;

public interface IDALException {
    class DALException extends Exception {

        private static final long serialVersionUID = 7355418246336739229L;
        public DALException(String msg) {
            super(msg);
        }
    }
}
