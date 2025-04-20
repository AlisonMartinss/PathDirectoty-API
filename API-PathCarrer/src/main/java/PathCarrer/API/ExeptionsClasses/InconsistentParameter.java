package PathCarrer.API.ExeptionsClasses;
/**
 * <ul>
 * <li><strong>InconsistentParameter:</strong> Parâmetros recebidos incoerentes com o que se espera. <br> Received parameters are inconsistent with what is expected.</li>
 * </ul>
 *
 *  **/
public class InconsistentParameter extends RuntimeException{
    public InconsistentParameter(String ErrorMessage) {
       super ("Error: " + ErrorMessage);
    }
}
