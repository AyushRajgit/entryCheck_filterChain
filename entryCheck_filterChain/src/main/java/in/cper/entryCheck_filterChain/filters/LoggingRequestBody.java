package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
@Order(2)
public class LoggingRequestBody implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest,1024 * 1024);

        filterChain.doFilter(requestWrapper,httpServletResponse);

        String reqBody = new String(requestWrapper.getContentAsByteArray());
        System.out.println("Request_Body : " + reqBody);
        System.out.println("---------------------Request Framing Done----------------------\n");
    }
}
