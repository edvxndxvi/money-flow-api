package br.com.fiap.money_flow_api.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.fiap.money_flow_api.service.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter{

    @Autowired
    private TokenService tokenService;

    // Esse método é um filtro que intercepta todas as requisições HTTP e verifica se o token de autenticação foi fornecido na requisição.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        var header = request.getHeader("Authorization");
        if(header == null ) {
            filterChain.doFilter(request, response);
            return;
        }

        if (!header.startsWith("Bearer ")) {
           response.setStatus(401);
           response.getWriter().write("""
                {"message": "Header deve iniciar com Bearer"}
           """);
           return;
        }

        var jwt = header.replace("Bearer ", "");

        var user = tokenService.getUserFromToken(jwt);

        var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        filterChain.doFilter(request, response);

    }
    
}
