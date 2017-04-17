package scripts.api;

/**
 * @author Encoded
 */
public interface Task {

    int priority();

    boolean validate();

    void execute();
    
    String action();

}