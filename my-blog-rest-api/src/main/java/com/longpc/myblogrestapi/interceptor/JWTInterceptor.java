package com.longpc.myblogrestapi.interceptor;

import com.longpc.myblogrestapi.bean.JwtCustomBean;
import com.longpc.myblogrestapi.constant.StringConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JWTInterceptor implements HandlerInterceptor {
    @Autowired
    JwtCustomBean jwtCustomBean;
    @Value("${host.frontend.uri}")
    String hostFrontend;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        response.setHeader("Access-Control-Allow-Origin", "http://103.125.170.20:10036");
        response.setHeader("Access-Control-Allow-Origin", hostFrontend);
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Expose-Headers", StringConstant.AUTH_TOKEN);
        if (
                !request.getServletPath().contains("auth")
                        || request.getServletPath().contains("editor")
                        || request.getServletPath().contains("authen")
                        || request.getServletPath().contains("chat")
                        || request.getServletPath().contains("ws-message")
                        || request.getServletPath().contains("app")
        ) {
            return true;
        }
        if (request.getMethod().equals("OPTIONS")) {
            return true;
        }
        if (!StringUtils.hasLength(request.getHeader(StringConstant.AUTH_TOKEN))) {
            response.sendError(403);
            return false;
        }
        String token = request.getHeader(StringConstant.AUTH_TOKEN);
        boolean isValidToken = jwtCustomBean.validateJwtToken(token);
        if (!isValidToken) {
            response.sendError(403);
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception exception) throws Exception {
    }
}
