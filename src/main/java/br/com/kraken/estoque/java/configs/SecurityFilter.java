package br.com.kraken.estoque.java.configs;

import br.com.kraken.estoque.java.repository.AcessoRepository;
import br.com.kraken.estoque.java.repository.EstoqueRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    private final TokenService tokenService;
    private final AcessoRepository repository;
    public SecurityFilter(TokenService tokenService, AcessoRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = recorverToken(request);
        if(token != null) {
            var subject = tokenService.Validation(token);
            var user = repository.findByUsuario(subject);

            var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String recorverToken(HttpServletRequest request){
        var getHeader = request.getHeader("authorization");
        if(getHeader == null) return null;
        return getHeader.replace("Bearer ", "");
    }
}
