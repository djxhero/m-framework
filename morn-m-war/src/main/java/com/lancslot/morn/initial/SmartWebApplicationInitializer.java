package com.lancslot.morn.initial;

import com.alibaba.fastjson.JSONObject;
import com.lancslot.morn.util.ConfigProps;
import com.lancslot.morn.util.GenInvokeResultUtil;
import com.lancslot.morn.constant.CommonResultCode;
import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.util.AssertionThreadLocalFilter;
import org.jasig.cas.client.util.HttpServletRequestWrapperFilter;
import org.jasig.cas.client.validation.Cas20ProxyReceivingTicketValidationFilter;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

public class SmartWebApplicationInitializer implements
		WebApplicationInitializer {

    /**
     * sso登录处理
     * @param servletContext
     * @throws ServletException
     */
    @Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		if (ConfigProps.isSSO()) {
            String casServerUrlPrefix = ConfigProps.getCasServerUrlPrefix();
            String casServerLoginUrl = ConfigProps.getCasServerLoginUrl();
            String serverName = ConfigProps.getServerName();
            String filterMapping = ConfigProps.getFilterMapping();
            String ignorePattern = ConfigProps.getIgnorePattern();

            servletContext.addListener(SingleSignOutHttpSessionListener.class);

            FilterRegistration.Dynamic singleSignOutFilter = servletContext.addFilter("CAS Single Sign Out Filter", SingleSignOutFilter.class);
            singleSignOutFilter.setInitParameter("casServerUrlPrefix", casServerUrlPrefix);
            singleSignOutFilter.addMappingForUrlPatterns(null, false, filterMapping);

            FilterRegistration.Dynamic authenticationFilter = servletContext.addFilter("CAS Filter", AuthenticationFilter.class);
            authenticationFilter.setInitParameter("casServerLoginUrl", casServerLoginUrl);
            authenticationFilter.setInitParameter("serverName", serverName);
			JSONObject data = new JSONObject();
			data.put("casServerLogoutUrl", ConfigProps.getCasServerLogoutUrl());
			JSONObject result = GenInvokeResultUtil.genFailureResponse("Session invalid!", CommonResultCode.NO_SESSION, data);
			authenticationFilter.setInitParameter("ajaxReturnJson", result.toJSONString());
            authenticationFilter.setInitParameter("ignorePattern", ignorePattern);
            authenticationFilter.addMappingForUrlPatterns(null, false, filterMapping);

            FilterRegistration.Dynamic ticketValidationFilter = servletContext.addFilter("CAS Validation Filter", Cas20ProxyReceivingTicketValidationFilter.class);
            ticketValidationFilter.setInitParameter("casServerUrlPrefix", casServerUrlPrefix);
            ticketValidationFilter.setInitParameter("serverName", serverName);
            ticketValidationFilter.addMappingForUrlPatterns(null, false, filterMapping);

            FilterRegistration.Dynamic requestWrapperFilter = servletContext.addFilter("CAS HttpServletRequest Wrapper Filter", HttpServletRequestWrapperFilter.class);
            requestWrapperFilter.addMappingForUrlPatterns(null, false, filterMapping);

            FilterRegistration.Dynamic assertionThreadLocalFilter = servletContext.addFilter("CAS Assertion Thread Local Filter", AssertionThreadLocalFilter.class);
            assertionThreadLocalFilter.addMappingForUrlPatterns(null, false, filterMapping);
        }
	}

}
