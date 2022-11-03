package ru.wdeath.testbotspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.wdeath.managerbot.lib.TelegramBotAutoConfig;
import ru.wdeath.managerbot.lib.charts.ChartTimestampMessage;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableCaching
public class TestBotSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestBotSpringApplication.class, args);
    }

}
