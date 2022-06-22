package ru.wdeath.testbotspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.wdeath.managerbot.lib.TelegramBotAutoConfig;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {TelegramBotAutoConfig.class})
@EnableCaching
public class TestBotSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBotSpringApplication.class, args);
    }
}
