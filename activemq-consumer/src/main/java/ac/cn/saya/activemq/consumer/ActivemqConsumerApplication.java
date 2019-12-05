package ac.cn.saya.activemq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActivemqConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqConsumerApplication.class, args);
    }

}
