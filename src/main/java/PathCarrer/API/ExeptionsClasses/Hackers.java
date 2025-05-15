package PathCarrer.API.ExeptionsClasses;
/**
 * <ul>
 *   <li><strong>PathAspectsUnexpected:</strong> Alguem está tentando se passar por outro, fazendo uso do token. <br> Someone is trying to impersonate someone else, using the token.</li>
 * </ul>
 **/
public class Hackers extends RuntimeException{
    public Hackers(String ErrorMessage) {
        super (ErrorMessage);
    }
}
