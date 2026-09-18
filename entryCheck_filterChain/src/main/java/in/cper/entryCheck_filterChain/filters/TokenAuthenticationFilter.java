package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(2)
public class TokenAuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String token = httpServletRequest.getHeader("token");

        if (token == null || !token.equals("randomTokenForTest")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            System.out.println("Invalid token");
            return;
        }

        System.out.println("Authentication Successful");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
