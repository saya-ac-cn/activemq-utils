package ac.cn.saya.activemq.producer.controller;

import ac.cn.saya.activemq.producer.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: MessageController
 * @ProjectName mq
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-29 17:44
 * @Description:
 */
@RestController
@RequestMapping(value = "message")
public class MessageController {

    @Autowired
    @Qualifier("producerService")
    private ProducerService producerService;

    @GetMapping("/topic/send")
    public String sendMessage(@RequestParam(value = "value") String value){
        producerService.sendMessage(value);
        return "消息："+value+"发送完成";
    }

}
