package com.lancslot.morn.web.interceptor;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;

/**
 * http://dinguangx.iteye.com/blog/2227049   此类由来
 *
 * RequestHolder.getRequest().getParameter()得不到参数值
 *
 * @create 2018-03-30 15:51
 **/

public class MultipartDispatcherServlet extends DispatcherServlet {
    @Override
    protected HttpServletRequest checkMultipart(HttpServletRequest request) throws MultipartException {
        HttpServletRequest servletRequest = super.checkMultipart(request);
        if(servletRequest instanceof MultipartHttpServletRequest
                && !(RequestContextHolder.currentRequestAttributes() instanceof MultipartServletRequestAttributes)) {
            RequestContextHolder.setRequestAttributes(new MultipartServletRequestAttributes(servletRequest));
        }
        return servletRequest;
    }

    public static class MultipartServletRequestAttributes extends ServletRequestAttributes {

        /**
         * Create a new ServletRequestAttributes instance for the given request.
         *
         * @param request current HTTP request
         */
        public MultipartServletRequestAttributes(HttpServletRequest request) {
            super(request);
        }
    }
}
