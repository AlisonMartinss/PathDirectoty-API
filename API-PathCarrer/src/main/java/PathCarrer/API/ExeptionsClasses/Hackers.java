package PathCarrer.API.ExeptionsClasses;

public class Hackers extends RuntimeException{
    public Hackers(String ErrorMessage) {
        super ("Error: " + ErrorMessage);
    }
}
