package cinema.config;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.annotation.PostConstruct;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

@EnableConfigurationProperties(CinemaConfig.class)
@Configuration
@PropertySource("classpath:cinema.properties")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
class PropsConfig { }

@Slf4j
@ConfigurationProperties("cinema")
@Validated
public record CinemaConfig(
        @Positive int totalRows,
        @Positive int totalColumns,
        @Positive  int defaultSeatPrice,
        @Positive int vipSeatPrice,
        @Positive  int numberOfFirstRows,
        @NotBlank String secret
) {
    @PostConstruct
    void logAfterInit() {
        log.info("totalRows = " + totalRows);
        log.info("totalColumns = " + totalColumns);
        log.info("defaultSeatPrice = " + defaultSeatPrice);
        log.info("vipSeatPrice = " + vipSeatPrice);
        log.info("numberOfFirstRows = " + numberOfFirstRows);
        log.info("secret = " + secret);
    }
}
