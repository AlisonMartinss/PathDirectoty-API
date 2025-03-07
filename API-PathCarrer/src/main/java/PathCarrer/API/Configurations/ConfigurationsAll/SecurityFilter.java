package PathCarrer.API.Configurations.ConfigurationsAll;

import PathCarrer.API.Configurations.TokenConfig.Token;
import PathCarrer.API.Repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private Token tokenservice;
    @Autowired
    private UserRepository userRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        var TokenJWT = getToken(request);
        if (TokenJWT != null){
            var TokenValid =  tokenservice.VerifyToken(TokenJWT);
            var User = userRepository.findByuserName(TokenValid);
            var authentication = new UsernamePasswordAuthenticationToken(User,null,User.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request,response);

    }

    private String getToken (HttpServletRequest JSON){
        var Token = JSON.getHeader("Authorization");
        if (Token != null){
            Token = Token.replace("Bearer","").trim();
            return Token;
        }
        return  null;
    }
}
