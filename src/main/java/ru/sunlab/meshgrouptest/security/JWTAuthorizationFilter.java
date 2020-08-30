package ru.sunlab.meshgrouptest.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import ru.sunlab.meshgrouptest.service.ErrorService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    private final ErrorService errorService;
    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, ErrorService errorService) {
        super(authenticationManager);
        this.errorService = errorService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Весь хардкод потом вынести в проперти
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer")) {
            chain.doFilter(request, response);
            return;
        }
        System.out.println("header: "+header);
        String name = null;
        try {
            name = JWT.require(Algorithm.HMAC256("secret"))
                    .build()
                    .verify(header.replace("Bearer ", ""))
                    .getClaim("name").asString();
        } catch (JWTVerificationException e) {
            String msg = e.getMessage();
            System.out.println("exception: "+msg);
            errorService.save(msg);
            chain.doFilter(request,response);
        }

        if (name != null) {
            System.out.println("name: "+name);
            SecurityContextHolder.getContext()
                    .setAuthentication(new UsernamePasswordAuthenticationToken(name,
                            null,
                            AuthorityUtils.createAuthorityList("ROLE_ADMIN")));
            chain.doFilter(request, response);
        }

    }

}
