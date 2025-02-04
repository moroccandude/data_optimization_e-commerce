package routines.system;


/**
 * The JSONException is thrown by the JSON.org classes when things are amiss.
 * 
 * @author JSON.org
 * @version 2008-09-18
 */
public class JSONException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 0;

    /**
     * Constructs a JSONException with an explanatory message.
     * 
     * @param message Detail about the reason for the exception.
     */
    public JSONException(String message) {
        super(message);
    }

    public JSONException(Throwable t) {
        super(t.getMessage(), t);
    }

}
