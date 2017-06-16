package dufrois;

/**
 * An exception that is thrown when the tournament has already started
 * 
 * @author Christian Dufrois
 * @version 2017.04.27
 */
public class TournamentStartedException extends Exception {

    private static final long serialVersionUID = 1L;
    private String message;

    public TournamentStartedException() {
        this("Tournament has already begun");
    }

    public TournamentStartedException(String msg) {
        message = msg;
    }

}
