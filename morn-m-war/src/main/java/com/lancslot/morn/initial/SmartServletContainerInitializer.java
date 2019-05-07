/**
 * 
 */
package com.lancslot.morn.initial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(WebApplicationInitializer.class)
public class SmartServletContainerInitializer implements
		ServletContainerInitializer {

	private static final Logger logger = LoggerFactory.getLogger(SmartServletContainerInitializer.class);

	
    @Override
	public void onStartup(Set<Class<?>> webApplicationInitializerClassSet, ServletContext servletContext)
			throws ServletException {
		try {
            System.out.println("SmartServletContainerInitializer onStartup");
            for (Class<?> webApplicationInitializerClass : webApplicationInitializerClassSet) {
                WebApplicationInitializer webApplicationInitializer = (WebApplicationInitializer) webApplicationInitializerClass.newInstance();
                webApplicationInitializer.onStartup(servletContext);
            }
        } catch (Exception e) {
            logger.error("初始化出错！", e);
        }
	}

}
