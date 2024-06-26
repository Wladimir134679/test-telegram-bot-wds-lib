package ru.wdeath.testbotspring.actions;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.db.UserTelegramEntity;
import ru.wdeath.managerbot.lib.service.UserTelegramService;
import ru.wdeath.programagent.lib.form.actions.FormActionHandler;
import ru.wdeath.programagent.lib.service.FormActionService;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendAllMessage  implements FormActionHandler {

    private final TelegramLongPollingEngine engine;
    private final UserTelegramService userTelegramService;
    private final FormActionService formActionService;

    @Override
    public String getTitle() {
        return "Рассылка сообщений";
    }

    @Override
    public String getName() {
        return "req-messages";
    }

    @Override
    public String getDescription() {
        return "Рассылка сообщений пользователям бота";
    }

    @Override
    public String getType() {
        return "tg-message";
    }

    @Override
    public String getText() {
        return "{\"text\":\"\", \"from\": [\"all\"]}";
    }

    @Override
    public void processingText(String text) {
        log.info("Start replay messages");
        Thread thread = new Thread(() -> {
            log.info("Start.......");

            List<UserTelegramEntity> all = userTelegramService.findAll();
            all.forEach(userTelegramEntity -> {
                SendMessage sendMessage = SendMessage
                        .builder()
                        .chatId(userTelegramEntity.getIdChatTelegram())
                        .text(text)
                        .build();
                try {
                    engine.execute(sendMessage);
                } catch (TelegramApiException e) {
                    log.error("UserTelegram send message error, {}", userTelegramEntity, e);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }, "Messages-Replay");
        thread.start();
    }
    @PostConstruct
    public void init(){
        log.info("Start init form action");
        formActionService.registration(this);
    }
}
