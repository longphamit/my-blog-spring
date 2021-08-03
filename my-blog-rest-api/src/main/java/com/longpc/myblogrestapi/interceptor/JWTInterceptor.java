package com.longpc.myblogrestapi.interceptor;

import com.longpc.myblogrestapi.bean.JwtCustomBean;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.addHeader("Access-Control-Expose-Headers", "X-LONGPC-ACCESS-TOKEN");
        if(
                !request.getServletPath().contains("auth")
                ||request.getServletPath().contains("editor")
                ||request.getServletPath().contains("authen")
        ){
            return true;
        }
        if(request.getMethod().equals("OPTIONS")){
            return true;
        }
        if (!StringUtils.hasLength(request.getHeader("X-LONGPC-ACCESS-TOKEN"))) {
            response.sendError(403);
            return false;
        }
        String token= request.getHeader("X-LONGPC-ACCESS-TOKEN");
        boolean isValidToken = jwtCustomBean.validateJwtToken(token);
        if(!isValidToken){
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
