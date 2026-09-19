package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
public class LoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;


        System.out.println("---------------Request Entry---------------");

        String API_Method = httpServletRequest.getMethod();
        String API_URL = httpServletRequest.getRequestURI();

        System.out.println("API_Method :  " + API_Method);
        System.out.println("API_URL :  " + API_URL);

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
