package hu.fuz;

import hu.fuz.bs.test.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */

@Configuration @EnableAutoConfiguration @ComponentScan
public class StartSpringBootApplication
{
    public static void main( String[] args ) {
        ConfigurableApplicationContext ctx = SpringApplication.run(StartSpringBootApplication.class);
//        ctx.getBean(Initializer.class).init();
    }
}
