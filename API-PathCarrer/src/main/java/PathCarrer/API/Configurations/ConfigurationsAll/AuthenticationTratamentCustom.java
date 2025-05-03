package PathCarrer.API.Configurations.ConfigurationsAll;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AuthenticationTratamentCustom implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        Throwable cause = authException.getCause();
        String mensagem;

        if (cause instanceof ExpiredJwtException || cause instanceof MalformedJwtException || cause instanceof SignatureException ) {
            mensagem = "{\"erro\": \"Token inválido ou expirado\"}";
        } else {
            mensagem = "{\"erro\": \"Token inválido ou expirado\"}";
        }

        response.getWriter().write(mensagem);
    }
}
