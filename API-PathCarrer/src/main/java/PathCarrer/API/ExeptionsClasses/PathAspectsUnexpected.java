package PathCarrer.API.ExeptionsClasses;
/**
 * <ul>
 *   <li><strong>PathAspectsUnexpected:</strong> Erro ao lidar com atributos do Path. <br> Error while handling some Path attribute or operation.</li>
 * </ul>
 **/
public class PathAspectsUnexpected extends RuntimeException{
    public PathAspectsUnexpected(String ErrorMessage) {
        super ("Error: " + ErrorMessage);
    }
}
