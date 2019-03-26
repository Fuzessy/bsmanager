package hu.fuz;

import hu.fuz.bs.test.Initializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class StartSpringBootApplication
{
    public static void main( String[] args ) {
        ConfigurableApplicationContext ctx = SpringApplication.run(StartSpringBootApplication.class);
        ctx.getBean(Initializer.class).init();
    }
}
