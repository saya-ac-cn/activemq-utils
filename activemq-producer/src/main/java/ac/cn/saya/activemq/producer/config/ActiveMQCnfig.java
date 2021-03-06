package ac.cn.saya.activemq.producer.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Title: ActiveMQCnfig
 * @ProjectName mq
 * @Description: TODO
 * @Author liunengkai
 * @Date: 2019-06-29 17:15
 * @Description:
 * activeMQ配置
 */
@Configuration
@EnableJms
public class ActiveMQCnfig {

//    @Value("${sub-queue}")
//    private String queueName;

//    @Bean
//    public Queue queue(){
//        return new ActiveMQQueue(queueName);
//    }


    @Value("${sub-topic}")
    private String topic;


    @Bean
    public Topic topic(){
        return new ActiveMQTopic(topic);
    }

}
