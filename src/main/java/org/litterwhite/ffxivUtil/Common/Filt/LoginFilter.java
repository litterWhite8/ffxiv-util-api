package org.litterwhite.ffxivUtil.Common.Filt;

import com.alibaba.fastjson2.JSON;
import org.litterwhite.ffxivUtil.Common.JWT.JwtTokenUtil;
import org.litterwhite.ffxivUtil.Common.Utils.AjaxResult;
import org.litterwhite.ffxivUtil.System.Service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Component
public class LoginFilter implements Filter {

    @Autowired
    private RedisService redisService;

    @Autowired
    private JwtTokenUtil jwtUtil;


    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getHeader("token");
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$","");
        if (whiteList.contains(path)){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        if(token != null && !token.isEmpty()){
            if (jwtUtil.validateToken(token)){
                System.out.println(jwtUtil.getUsernameFromToken(token));
                filterChain.doFilter(servletRequest,servletResponse);
            }else{
                returnNoLogin(servletResponse,"token已失效");

            }
        }else{
            returnNoLogin(servletResponse,"未登录,请先登录");
        }
    }

    private List<String> whiteList = Arrays.asList("/system/auth/login");

    private void returnNoLogin(ServletResponse response,String msg) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        ServletOutputStream outputStream = httpServletResponse.getOutputStream();
        // 设置返回401 和响应编码
        httpServletResponse.setStatus(401);
        httpServletResponse.setContentType("Application/json;charset=utf-8");
        // 构造返回响应体
        AjaxResult result = new AjaxResult(401,msg);
        String resultString = JSON.toJSONString(result);
        outputStream.write(resultString.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void destroy() {
    }

}
