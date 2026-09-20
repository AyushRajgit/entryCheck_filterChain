package in.cper.entryCheck_filterChain.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Component
@Order(4)
public class ModifyResponseFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;

        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);

        System.out.println("\n-------------Response Framing Started-----------------");

        String UniqueID = UUID.randomUUID().toString();
        httpServletResponse.setHeader("Your-Unique-ID", UniqueID);
        System.out.println("UniqueID :   " + UniqueID);

        filterChain.doFilter(servletRequest, responseWrapper);

        String body = new String(responseWrapper.getContentAsByteArray());

        String modifiedBody = """
                {
                   "OriginalResponse" : %s,
                   "UniqueID" : %s,
                }
                """.formatted(body,UniqueID);

        System.out.println("modifiedBody :   " + modifiedBody);

        responseWrapper.resetBuffer();
        responseWrapper.getWriter().write(modifiedBody);
        responseWrapper.copyBodyToResponse();

        System.out.println("---------------------Reponse Framing Done------------------------");
    }
}
