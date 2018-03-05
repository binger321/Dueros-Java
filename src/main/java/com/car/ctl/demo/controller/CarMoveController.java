package com.car.ctl.demo.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/1/13
 * Time: 上午11:08
 * To change this template use File | Settings | File Templates.
 * Description:
 */

@RestController(value = "小车控制")
@RequestMapping(value = "carControl")
public class CarMoveController {
    @RequestMapping(value = "/carMove")
    public String carMove(@RequestBody String data) throws IOException {
        // 根据request创建Bot
        System.out.println(data);
        CarMoveBot bot = new CarMoveBot(data);

        // 打开签名验证
        // bot.enableVerify();

        // 线下调试时，可以关闭签名验证
        bot.disableVerify();

        try {

//            // 调用bot的run方法
            String responseJson = bot.run();
            return responseJson;
//            // 设置response的编码UTF-8
//            response.setCharacterEncoding("UTF-8");
//            // 返回response
//            response.getWriter().append(responseJson);
        } catch (Exception e) {
            return "{\"status\":1,\"msg\":\"\"}";
        }
    }
}
