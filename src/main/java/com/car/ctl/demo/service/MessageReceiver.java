package com.car.ctl.demo.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/3/5 0005
 * Time: 13:11
 * To change this template use File | Settings | File Templates.
 * Description:
 */
@Component
@RabbitListener(queues = "car.action")
public class MessageReceiver {

}