package ac.cn.saya.activemq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class ActivemqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivemqProducerApplication.class, args);
    }

}
