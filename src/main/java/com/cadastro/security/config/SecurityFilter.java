package com.cadastro.security.config;

import com.cadastro.security.models.Usuario;
import com.cadastro.security.repositories.UsuarioRepository;
import com.cadastro.security.services.UtenticacaoService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UsuarioRepository usuarioRepository;
    private final UtenticacaoService utenticacaoService;

    @Autowired
    SecurityFilter(UtenticacaoService utenticacaoService, UsuarioRepository usuarioRepository) {
        this.utenticacaoService = utenticacaoService;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = extraiTokenMeader(request);
        if (token != null) {
            String login = utenticacaoService.validaTokenJwt(token);
            if (login != null) {
                Usuario usuario = usuarioRepository.findByLogin(login);
                if (usuario != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        filterChain.doFilter(request, response);
    }

    public String extraiTokenMeader(HttpServletRequest request) {
        var authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);  // Remove "Bearer " e retorna o token
        }
        return null;
    }
}
