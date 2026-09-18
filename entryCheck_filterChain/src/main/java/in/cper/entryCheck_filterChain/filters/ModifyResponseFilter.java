package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(3)
public class ModifyResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        String UniqueID = UUID.randomUUID().toString();
        httpServletResponse.setHeader("Your-Unique-ID", UniqueID);
        System.out.println("Unique ID :  " + UniqueID + " assigned to user.");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
