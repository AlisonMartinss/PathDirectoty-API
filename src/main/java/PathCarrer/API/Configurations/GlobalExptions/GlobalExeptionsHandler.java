package PathCarrer.API.Configurations.GlobalExptions;

import PathCarrer.API.ExeptionsClasses.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@RestControllerAdvice
public class GlobalExeptionsHandler {
    @ExceptionHandler(AccessDeniedException.class)
     public ResponseEntity<?> handleAccessDenied(AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("erro", "Acesso negado " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
     public ResponseEntity<?> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("erro",ex.getMessage()));
    }

    @ExceptionHandler(Hackers.class)
    public ResponseEntity<?> handleHackers(Hackers ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(GenericErro.class)
    public static ResponseEntity<?> handleMyGeneric(GenericErro ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(InconsistentParameter.class)
    public ResponseEntity<?> handleInconsistentParameter(InconsistentParameter ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<?> handleNotFound(NotFound ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("erro", ex.getMessage()));
    }

    @ExceptionHandler(PathAspectsUnexpected.class)
    public ResponseEntity<?> handlePathAspectsUnexpected(PathAspectsUnexpected ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }
    @ExceptionHandler(UserAspectsUnexpected.class)
    public ResponseEntity<?> handleUserAspectsUnexpected(UserAspectsUnexpected ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("erro", ex.getMessage()));
    }

}
