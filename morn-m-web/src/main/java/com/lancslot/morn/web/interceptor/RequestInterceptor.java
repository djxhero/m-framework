package com.lancslot.morn.web.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
public class RequestInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(
            RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
//        JSONObject requestParam = new JSONObject();
//        requestParam.put("requestParam", request.getParameterMap());
//        logger.info("RequestInterceptor preHandle {}, {}, {}, requestParam: {}",
//                request.getContextPath(), request.getRequestURL(), request.getMethod(), requestParam.toJSONString());
//
//        HttpSession httpSession = request.getSession();
//
//        // 本地测试时，如果cas未开，默认给一个帐号
//        if (!ConfigProps.isSSO()) {
//            httpSession.setAttribute("accountName", "aaaa");
//        }
//
//        String userName = (String) httpSession.getAttribute("accountName");
//
//        if (StringUtils.isBlank(userName)) {
//            AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();
//            // 服务器重启时，其他浏览器停留在页面，做提交操作时，principal为空
//            if (principal != null) {
//                userName = principal.getName();
//                // 补充session
//                if (StringUtils.isNotEmpty(userName)) {
//                    String accountName = userName;
//                    logger.info("补充m端session的accountName{}", accountName);
//                    httpSession.setAttribute("accountName", accountName);
//                }
//            }
//        }
//
//        logger.info("RequestInterceptor userName {}", userName);
//
//        if (StringUtils.isBlank(userName)) {
//            PrintWriter out = null;
//            try {
//                JSONObject data = new JSONObject();
//                data.put("casServerLogoutUrl", ConfigProps.getCasServerLogoutUrl());
//                JSONObject result = GenInvokeResultUtil.genFailureResponse(ResultCode.CODE_NO_SESSION, data);
//                logger.info("RequestInterceptor session invalid: {}", result.toJSONString());
//                out = response.getWriter();
//                out.write(result.toJSONString());
//                out.flush();
//            } catch (Exception e) {
//                logger.error(e.getMessage(), e);
//            } finally {
//                IOUtils.closeQuietly(out);
//            }
//
//            return false;
//        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws
            Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
                                Exception ex) throws
            Exception {

    }

}
