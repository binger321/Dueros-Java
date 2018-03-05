package com.car.ctl.demo.controller;

import com.baidu.dueros.bot.BaseBot;
import com.baidu.dueros.data.request.IntentRequest;
import com.baidu.dueros.data.request.LaunchRequest;
import com.baidu.dueros.data.request.SessionEndedRequest;
import com.baidu.dueros.data.response.OutputSpeech;
import com.baidu.dueros.data.response.Reprompt;
import com.baidu.dueros.data.response.card.TextCard;
import com.baidu.dueros.model.Response;
import com.car.ctl.demo.bean.CarAction;
import com.car.ctl.demo.service.MessageSender;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: zhuyubin
 * Date: 2018/1/13
 * Time: 上午11:19
 * To change this template use File | Settings | File Templates.
 * Description:
 */
public class CarMoveBot extends BaseBot{
    /**
     * 重写BaseBot构造方法
     *
     * @param request
     *            servlet Request作为参数
     * @throws IOException
     *             抛出异常
     */
    public CarMoveBot(HttpServletRequest request) throws IOException {
        super(request);
    }

    /**
     * 重写BaseBot构造方法
     *
     * @param request
     *            Request字符串
     * @throws IOException
     *             抛出异常
     */
    public CarMoveBot(String request) throws IOException {
        super(request);
    }

    /**
     * 重写onLaunch方法，处理onLaunch对话事件
     *
     * @param launchRequest
     *            LaunchRequest请求体
     * @see com.baidu.dueros.bot.BaseBot#onLaunch(com.baidu.dueros.data.request.LaunchRequest)
     */
    @Override
    protected Response onLaunch(LaunchRequest launchRequest) {

        // 新建文本卡片
        TextCard textCard = new TextCard("欢迎进入小车控制系统");
        // 设置链接地址
        textCard.setUrl("www:....");
        // 设置链接内容
        textCard.setAnchorText("setAnchorText");
        // 添加引导话术
        textCard.addCueWord("欢迎进入");

        // 新建返回的语音内容
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "小车控制系统为您服务");

        // 构造返回的Response
        Response response = new Response(outputSpeech, textCard);

        return response;
    }

    /**
     * 重写onIntent方法，处理onIntent对话事件
     *
     * @param intentRequest
     *            IntentRequest请求体
     * @see com.baidu.dueros.bot.BaseBot#onInent(com.baidu.dueros.data.request.IntentRequest)
     */
    @Override
    protected Response onInent(IntentRequest intentRequest) {

        // 判断NLU解析的意图名称是否匹配 inquiry
        if ("car_action".equals(intentRequest.getIntentName())) {
            // 判断NLU解析解析后是否存在这个槽位
            if (getSlot("car_direction") == null) {
                // 询问月薪槽位car_action
                ask("car_action");
                return askDirection();
            } else if (getSlot("car_distance") == null) {
                // 询问城市槽位car_speed
                ask("car_distance");
                return askSpeed();
            } else {
                // 槽位完整
                return complete();
            }
        }

        return null;
    }

    /**
     * 重写onSessionEnded事件，处理onSessionEnded对话事件
     *
     * @param sessionEndedRequest
     *            SessionEndedRequest请求体
     * @see com.baidu.dueros.bot.BaseBot#onSessionEnded(com.baidu.dueros.data.request.SessionEndedRequest)
     */
    @Override
    protected Response onSessionEnded(SessionEndedRequest sessionEndedRequest) {

        // 构造TextCard
        TextCard textCard = new TextCard("感谢使用语音控制小车");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("欢迎再次使用");

        // 构造OutputSpeech
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "欢迎再次使用语音控制小车");

        // 构造Response
        Response response = new Response(outputSpeech, textCard);

        return response;
    }

    /**
     * 询问城市信息
     *
     * @return Response 返回Response
     */
    private Response askDirection() {

        TextCard textCard = new TextCard("您想往哪去呢?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("您想去哪呢?");

        setSessionAttribute("key_1", "value_1");
        setSessionAttribute("key_2", "value_2");

        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "您想去哪呢?");

        Reprompt reprompt = new Reprompt(outputSpeech);

        Response response = new Response(outputSpeech, textCard, reprompt);

        return response;
    }

    /**
     * 询问月薪
     *
     * @return Response 返回Response
     */
    private Response askSpeed() {

        TextCard textCard = new TextCard("您的速度是多少呢?");
        textCard.setUrl("www:......");
        textCard.setAnchorText("链接文本");
        textCard.addCueWord("您的速度是多少呢?");

        // 设置会话信息
        setSessionAttribute("key_1", "value_1");
        setSessionAttribute("key_2", "value_2");

        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, "您的速度是多少呢?");

        // 构造reprompt
        Reprompt reprompt = new Reprompt(outputSpeech);

        Response response = new Response(outputSpeech, textCard, reprompt);

        return response;
    }




    /**
     * 槽位完整
     *
     * @return Response
     */
    private Response complete() {
        // 获取多轮槽位值：小车方向 小车距离
        String direction = getSlot("car_direction");
        String distance = getSlot("car_distance");
        String ret = "我知道了";

        TextCard textCard = new TextCard(ret);
        textCard.setAnchorText("setAnchorText");
        textCard.addCueWord("查询成功");

        setSessionAttribute("key_1", "value_1");
        setSessionAttribute("key_2", "value_2");


        CarAction carAction = new CarAction();
        carAction.setCode("1号");
        carAction.setDirection(direction);
        carAction.setDisctance(distance);
        MessageSender messageSender = new MessageSender();
        messageSender.send(carAction);
        OutputSpeech outputSpeech = new OutputSpeech(OutputSpeech.SpeechType.PlainText, ret);

        Reprompt reprompt = new Reprompt(outputSpeech);

        // 主动结束会话
        this.endDialog();

        Response response = new Response(outputSpeech, textCard, reprompt);

        return response;
    }
}
