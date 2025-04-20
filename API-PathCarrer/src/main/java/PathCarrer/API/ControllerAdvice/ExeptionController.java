package PathCarrer.API.ControllerAdvice;

import PathCarrer.API.ExeptionsClasses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Representa um conjunto de exceções personalizadas utilizadas em diferentes contextos de erro.
 *
 * <p>
 * Represents a set of custom exceptions used in different error contexts.
 * </p>
 *
 * <p><strong>Tipos de exceção / Exception types:</strong></p>
 *
 * <ul>
 *   <li><strong>GenericError:</strong> Erro genérico, sem especificações.
 *       Generic error without specific details.</li>
 *   <li><strong>InconsistentParameter:</strong> Parâmetros recebidos incoerentes com o que se espera.
 *       Received parameters are inconsistent with what is expected.</li>
 *   <li><strong>NotFound:</strong> Utilizado quando uma busca no banco de dados falha.
 *       Used when a database query fails (e.g., entity not found).</li>
 *   <li><strong>UserAspectsUnexpected:</strong> Erro ao lidar com atributos do usuário.
 *       Error while handling some user attribute or operation.</li>
 *   <li><strong>PathAspectsUnexpected:</strong> Erro ao lidar com atributos do Path.
 *   <br> Error while handling some Path attribute or operation.</li>
 * </ul>
 *
 * <p>
 * Essas exceções permitem uma comunicação mais clara entre a aplicação e a API/restante do sistema,
 * facilitando o tratamento e retorno de mensagens adequadas ao cliente.
 * </p>
 *
 * <p>
 * These exceptions enable clearer communication between the application and the API/client,
 * making it easier to handle and return appropriate messages.
 * </p>
 */


@ControllerAdvice
public class ExeptionController {

    @ExceptionHandler(GenericErro.class)
    public ResponseEntity<String> handleGenericErro(GenericErro exeption) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exeption.getMessage());
    }

    @ExceptionHandler(InconsistentParameter.class)
    public ResponseEntity<String> handleInconsistentParameter(InconsistentParameter exeption) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exeption.getMessage());
    }
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<String> handleNotFound(NotFound exeption) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exeption.getMessage());
    }

    @ExceptionHandler(UserAspectsUnexpected.class)
    public ResponseEntity<String> handleUserAspectsUnexpected(UserAspectsUnexpected exeption) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exeption.getMessage());
    }

    @ExceptionHandler(PathAspectsUnexpected.class)
    public ResponseEntity<String> handleUserAspectsUnexpected(PathAspectsUnexpected exeption) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exeption.getMessage());
    }

}
