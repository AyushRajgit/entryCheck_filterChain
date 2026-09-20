package in.cper.entryCheck_filterChain.InterceptorConfig;

import in.cper.entryCheck_filterChain.interceptor.AuthorizationCheck;
import in.cper.entryCheck_filterChain.interceptor.LoggingControllerExecutionTime;
import in.cper.entryCheck_filterChain.interceptor.LoggingControllerInfo;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private LoggingControllerInfo loggingControllerInfo;
    private LoggingControllerExecutionTime loggingControllerExecutionTime;
    private AuthorizationCheck authorizationCheck;

    public InterceptorConfiguration(LoggingControllerInfo loggingControllerInfo, LoggingControllerExecutionTime loggingControllerExecutionTime) {
        this.loggingControllerInfo = loggingControllerInfo;
        this.loggingControllerExecutionTime = loggingControllerExecutionTime;
        this.authorizationCheck = new AuthorizationCheck();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationCheck).order(1);
        registry.addInterceptor(loggingControllerInfo).order(2);
        registry.addInterceptor(loggingControllerExecutionTime).order(3);
    }
}
