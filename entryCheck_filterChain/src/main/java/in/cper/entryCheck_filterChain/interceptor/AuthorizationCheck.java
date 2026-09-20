package in.cper.entryCheck_filterChain.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.HandlerInterceptor;

@Controller
public class AuthorizationCheck implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String authKey = request.getHeader("auth-key");

        if (authKey == null || !authKey.equals("Admin")) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.setContentType ("application/json");
            response.getWriter().write("\n" +
                    "{\n" +
                    "    \"message\" : \"You are not authorized to use this resource\"\n" +
                    "}");
            return false;
        }

        System.out.println("\nUser is Authorized to use this resource\n");
        return true;
    }
}
