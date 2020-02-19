package top.chenzicong.highconcurrency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class JwtLoginFilter extends OncePerRequestFilter {
    @Value("jwt.tokenHeader")
    private String tokenHeader;

    @Value("jwt.tokenHead")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//        UserDetails userDetails = userDetailsService.loadUserByUsername("czc");
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, null);
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//        UserDetails userDetails = userDetailsService.loadUserByUsername("user");
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, null);
//        SecurityContextHolder.getContext().setAuthentication(token);
        filterChain.doFilter(request, response);

    }
}
