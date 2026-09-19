package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(5)
public class ExecutionTimeFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        long startTime = System.currentTimeMillis();

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        catch (Exception e) {
            httpServletResponse.setStatus(500);
            System.out.println("Exception in ExecutionTimeFilter : " +  e.getMessage());
        }

        finally {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.out.println("API-execution-time-report : " + duration + "ms");
        }

    }
}
