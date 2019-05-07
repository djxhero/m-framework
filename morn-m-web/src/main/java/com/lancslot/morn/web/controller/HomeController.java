package com.lancslot.morn.web.controller;

import com.lancslot.morn.web.controller.base.AbstractController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 乌尔肯问答m端接口
 * @Date 2019/1/22 10:39
 **/
@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {
    Logger logger = LoggerFactory.getLogger(HomeController.class);

    /**
     * 首页
     */
    @RequestMapping(value = "/home")
    @ResponseBody
    public Object home() {
        Map<String, Object> resultMap = new HashMap<String, Object>(1);
        resultMap.put("xxxx","1111111");
        return buildSuccess(resultMap);
    }

}
