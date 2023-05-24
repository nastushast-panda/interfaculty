package cinema;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;

@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        var context = SpringApplication.run(Main.class, args);
        var datasource = context.getBean(DataSource.class);
        log.info("ds = {}", datasource);
    }
}
