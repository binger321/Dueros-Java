package com.car.ctl.demo.service;

import com.car.ctl.demo.common.MyEasyJsonUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/3/3
 * Time: 下午5:16
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send(Object object){
        String actionJson = MyEasyJsonUtil.json2string(object);
        System.out.println(actionJson);
        this.rabbitTemplate.convertAndSend("carAction",actionJson);
    }
}
