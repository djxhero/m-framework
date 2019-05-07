package com.lancslot.morn.web.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * session controller基类
 */
public abstract class BaseSessionController extends AbstractController {

    protected Logger logger = LoggerFactory.getLogger(BaseSessionController.class);

    protected Integer getAccountId( HttpServletRequest httpServletRequest){
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            Integer accountId = (Integer) httpSession.getAttribute("accountId");
            if(accountId != null){
                return accountId;
            }
        } catch (Exception e) {
            logger.error("getAccountId error", e);
        }
        return 0;
    }
    protected String getAccountName( HttpServletRequest httpServletRequest){
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            String accountName = (String) httpSession.getAttribute("accountName");
            if (StringUtils.hasText(accountName)) {
                return accountName.split("@")[0];
            }
        } catch (Exception e) {
            logger.error("getOperator error", e);
        }
        return null;
    }
    protected Integer getOrgId(HttpServletRequest httpServletRequest) {
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            return (Integer) httpSession.getAttribute("orgId");
        } catch (Exception e) {
            logger.error("getAccountOrgId error", e);
        }
        return 0;
    }

    protected String getOrgName(HttpServletRequest httpServletRequest) {
        try {
            HttpSession httpSession = httpServletRequest.getSession();
            return  (String) httpSession.getAttribute("orgName");
        } catch (Exception e) {
            logger.error("getOperator error", e);
        }
        return null;
    }
}
