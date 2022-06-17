package ru.wdeath.testbotspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.wdeath.managerbot.lib.WdsManagerBotLibApplication;
import ru.wdeath.managerbot.lib.bot.TelegramBotApiStarterService;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TestBotSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestBotSpringApplication.class, args);
	}
}
