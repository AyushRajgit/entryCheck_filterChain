package in.cper.entryCheck_filterChain.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoggingControllerInfo implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("----------------LoggingControllerInfo : Entry--------------------");
        System.out.println("API_Method : " +  request.getMethod());
        System.out.println("API_endpoint : " +  request.getRequestURI());
        System.out.println("IP Address : " + request.getRemoteAddr());

        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            System.out.println("Controller Class : " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method : " + handlerMethod.getMethod().getName());
        }

        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("----------------LoggingControllerInfo : Exit--------------------");
    }
}
