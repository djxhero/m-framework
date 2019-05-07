package com.lancslot.morn.web.aop;

import com.lancslot.morn.utils.constant.BusinessConstants;
import com.lancslot.morn.utils.constant.CommonResultCode;
import com.lancslot.morn.web.aop.annotation.*;
import com.lancslot.morn.web.exception.ParameterException;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Component
@Aspect
public class ParamsValidAspect {

    /**
     * @param point
     * @param paramsValid
     * @throws Exception
     * @description 方法参数校验
     */
    @Before("within(com.lancslot.morn.web.controller..*) && @annotation(paramsValid)")
    public void paramsValid(JoinPoint point, ParamsValid paramsValid) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            notBlank(paramsValid.notBlank(), request);
            date(paramsValid.dates(), request);
            length(paramsValid.lengths(), request);
            pattern(paramsValid.patterns(), request);
            range(paramsValid.ranges(), request);
        } catch (Exception e) {
            throw new ParameterException(
                    StringUtils.isBlank(e.getMessage()) ? CommonResultCode.CODE_PARAM.msg : e.getMessage());
        }
    }

    /**
     * @param notBlank
     * @param request
     * @description 非空校验
     */
    private void notBlank(NotBlank notBlank, HttpServletRequest request) {
        if (notBlank == null) {
            return;
        }
        for (String name : notBlank.names()) {
            isBlank(name, request.getParameter(name), notBlank);
        }
    }

    /**
     * @param name
     * @param value
     * @param notBlank
     * @description 非空校验
     */
    private void isBlank(String name, String value, NotBlank notBlank) {
        if (StringUtils.isBlank(value)) {
            throw new ParameterException(name + notBlank.tips());
        }
    }

    /**
     * @param lengths
     * @param request
     * @description 长度校验
     */
    private void length(Length[] lengths, HttpServletRequest request) {
        if (lengths == null) {
            return;
        }
        for (Length length : lengths) {
            isLength(request.getParameter(length.name()), length);
        }
    }

    /**
     * @param dates
     * @param request
     * @description 日期校验
     */
    private void date(Date[] dates, HttpServletRequest request) {
        if (dates == null) {
            return;
        }
        for (Date date : dates) {
            isDate(request.getParameter(date.name()), date);
        }
    }

    /**
     * @param value
     * @param date
     * @description 日期校验
     */
    private void isDate(String value, Date date) {
        if (StringUtils.isBlank(value)) {
            throw new ParameterException(date.tips());
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        sdf.setLenient(false);
        java.util.Date time = null;
        if (!StringUtils.isBlank(date.format())) {
            sdf.applyPattern(date.format());
        }
        try {
            time = sdf.parse(value, pos);
        } catch (Exception e) {
            throw new ParameterException(date.tips());
        }
        if (time == null || pos.getErrorIndex() > 0) {
            throw new ParameterException(date.tips());
        }
        if (pos.getIndex() != value.length()) {
            throw new ParameterException(date.tips());
        }
        if (sdf.getCalendar().get(Calendar.YEAR) < 1000 || sdf.getCalendar().get(Calendar.YEAR) > 9999) {
            throw new ParameterException(date.tips());
        }
    }

    /**
     * @param value
     * @param length
     * @description 长度校验
     */
    private void isLength(String value, Length length) {
        if (StringUtils.isBlank(value) || value.length() > length.size()) {
            throw new ParameterException(length.tips());
        }
    }

    /**
     * @param patterns
     * @param request
     * @description 正则校验
     */
    private void pattern(Pattern[] patterns, HttpServletRequest request) {
        if (patterns == null) {
            return;
        }
        for (Pattern pattern : patterns) {
            isPattern(request.getParameter(pattern.name()), pattern);
        }
    }

    /**
     * @param value
     * @param pattern
     * @description 正则校验
     */
    private void isPattern(String value, Pattern pattern) {
        if (StringUtils.isBlank(value) || !value.matches(pattern.regex())) {
            throw new ParameterException(pattern.tips());
        }
    }

    /**
     * @param ranges
     * @param request
     * @description 范围校验
     */
    private void range(Range[] ranges, HttpServletRequest request) {
        if (ranges == null) {
            return;
        }
        for (Range range : ranges) {
            isRange(request.getParameter(range.name()), range);
        }
    }

    /**
     * @param value
     * @param range
     * @description 范围校验
     */
    private void isRange(String value, Range range) {
        if (StringUtils.isBlank(value) || !value.matches(BusinessConstants.REGEX_INTEGER)
                || Integer.parseInt(value) < range.min()
                || Integer.parseInt(value) > range.max()) {
            throw new ParameterException(range.tips());
        }
    }

}
