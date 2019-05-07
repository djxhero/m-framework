package com.lancslot.morn.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {

    static Logger logger = LoggerFactory.getLogger(IpUtil.class);

    /**
     *
     * 功能描述：获取请求ip信息
     */
    public static String getRequestIp(HttpServletRequest request) {

        String srcIp = request.getHeader("X-Real-IP");
        if (!isInnerIp(srcIp) && StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            return srcIp;
        }

        srcIp = request.getHeader("x-forwarded-for");
        if (StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            String ips[] = srcIp.split(",");
            for (String ip : ips) {
                if (!isInnerIp(ip) && !"unknown".equalsIgnoreCase(ip)) {
                    return ip;
                }
            }

        }
        srcIp = request.getHeader("Proxy-Client-IP");
        if (!isInnerIp(srcIp) && StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            return srcIp;
        }

        srcIp = request.getHeader("WL-Proxy-Client-IP");
        if (!isInnerIp(srcIp) && StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            return srcIp;
        }

        srcIp = request.getHeader("HTTP_CLIENT_IP");
        if (!isInnerIp(srcIp) && StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            return srcIp;
        }

        srcIp = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!isInnerIp(srcIp) && StringUtils.hasText(srcIp) && !"unknown".equalsIgnoreCase(srcIp)) {
            return srcIp;
        }
        return request.getRemoteAddr();
    }

    public static boolean isInnerIp(String ip) {
        return "127.0.0.1".equals(ip);
    }
}
