package PathCarrer.API.ExeptionsClasses;
/**
 * <ul>
 *   <li><strong>UserAspectsUnexpected:</strong> Erro ao lidar com atributos do usuário. <br> Error while handling some user attribute or operation.</li>
 * </ul>
 **/
public class UserAspectsUnexpected extends RuntimeException{
    public UserAspectsUnexpected(String ErrorMessage) {
        super ("Error: " + ErrorMessage);
    }
}
