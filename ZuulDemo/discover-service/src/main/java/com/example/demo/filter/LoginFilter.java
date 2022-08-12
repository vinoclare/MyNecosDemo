package com.example.demo.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class LoginFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());


    // PRE类型
    @Override
    public String filterType(){
        return FilterConstants.PRE_TYPE;
    }

    // 执行顺序
    @Override
    public int filterOrder(){
        return 0;
    }

    // 是否生效
    @Override
    public boolean shouldFilter(){
        return true;
    }


    // 业务逻辑-登录校验-token
    // 从request中取出token（query参数形式），判断并做相应判断和响应。
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info(String.format("[%s] %s process", request.getRequestURL().toString(), request.getMethod()));

        String token = request.getParameter("token");

        if (StringUtils.isBlank(token)) {
            logger.warn("token is empty");

            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);

            try {
                ctx.getResponse().getWriter().write("token is empty.");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        else if (!token.equals("11")){
            logger.warn("PassWord Error!");

            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);

            try {
                ctx.getResponse().getWriter().write("PassWord Error!");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
        return null;
    }

}
