package ac.cn.saya.activemq.producer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import javax.jms.Topic;
import java.util.UUID;

/**
 * @Title: ProducerService
 * @ProjectName mq
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-29 17:20
 * @Description:
 */
@Service(value = "producerService")
public class ProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;


    @Autowired
    private Topic topic;

    public void sendMessage(String message){
        //队列投送
        /// jmsMessagingTemplate.convertAndSend(queue,"*****"+ message + UUID.randomUUID().toString().substring(0,6));
        jmsMessagingTemplate.convertAndSend(topic,"发布公众号消息"+ message + UUID.randomUUID().toString().substring(0,6));
    }

}
