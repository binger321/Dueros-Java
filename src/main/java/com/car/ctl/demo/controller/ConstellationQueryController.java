package com.car.ctl.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/1/13
 * Time: 上午11:23
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Api(value = "constellation",description = "星座查询", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController(value = "星座查询")
@RequestMapping(value = "constellation")
public class ConstellationQueryController {

    @ApiOperation(value = "星座查询")
    @RequestMapping(value = "/query")
    public String query(@RequestBody String data) throws IOException {
//        System.out.println(request.getQueryString());
//        System.out.println(request.getServletContext());
//        System.out.println(request.getContextPath());
        // 根据request创建Bot
//        if (data != null && data.length() > 0){
//            return "{\"status\":1,\"msg\":\"入参为空！！\"}";
//        }
        System.out.println(data);
        ConstellationBot bot = new ConstellationBot(data);

        // 打开签名验证
        // bot.enableVerify();

        // 线下调试时，可以关闭签名验证
        bot.disableVerify();

        try {
            // 调用bot的run方法
            String responseJson = bot.run();
            // 设置response的编码UTF-8
//            response.setCharacterEncoding("UTF-8");
            // 返回response
//            response.getWriter().append(responseJson);
            return responseJson;
//            System.out.println("已结束：" +responseJson);
//            return responseJson;
        } catch (Exception e) {
//            response.getWriter().append("{\"status\":1,\"msg\":" + e.getMessage() + "\"\"}");
            return "{\"status\":1,\"msg\":\"\"}";
        }
    }
}
