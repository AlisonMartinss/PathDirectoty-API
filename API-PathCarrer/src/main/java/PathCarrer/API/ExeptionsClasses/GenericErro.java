package PathCarrer.API.ExeptionsClasses;

/**
 * <ul>
 *   <li><strong>GenericError:</strong> Erro genérico, sem especificações. <br> Generic error without specific details.</li>
 * </ul>
 * **/
public class GenericErro extends RuntimeException{
    public GenericErro(String ErrorMessage) {
        super ("Error: " + ErrorMessage);
    }

}
