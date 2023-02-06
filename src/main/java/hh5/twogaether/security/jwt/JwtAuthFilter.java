package hh5.twogaether.security.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hh5.twogaether.security.jwt.dto.SecurityExceptionDto;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private static List<String> permitUrl = List.of("/users/login","/users/signup");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        if (permitUrl.contains(uri)) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = jwtUtil.resolveToken(request);

        if(token != null) {
            if(!jwtUtil.validateToken(token)){
                jwtExceptionHandler(response, "토큰이 유효하지 않습니다.", HttpStatus.UNAUTHORIZED.value());
                return;
            }

            Claims info = jwtUtil.getUserInfoFromToken(token);

            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request, response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);

        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

    public void jwtExceptionHandler(HttpServletResponse response, String message, int status) {
        response.setStatus(status);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(status, message));
            response.getWriter().write(json);
        }

        catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}