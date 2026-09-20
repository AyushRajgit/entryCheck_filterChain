package in.cper.entryCheck_filterChain.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

@Controller
public class LoggingControllerExecutionTime implements HandlerInterceptor {

    private long startTime;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        startTime = System.currentTimeMillis();
        return true;
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n--------------LoggingControllerExecutionTime : in milli-Second----------------");
        System.out.println("Execution Time : " + duration + " ms");
        System.out.println("------------------------------------------------------------------------------\n");
    }
}
