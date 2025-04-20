package PathCarrer.API.ExeptionsClasses;
/**
 * <ul>
    </li>
 *   <li><strong>NotFound:</strong> Utilizado quando uma busca no banco de dados falha. <br> Used when a database query fails (e.g., entity not found).</li>
 *  </ul>
 **/
public class NotFound extends RuntimeException{
    public NotFound(String ErrorMessage) {
        super ("Error: " + ErrorMessage);
    }
}
