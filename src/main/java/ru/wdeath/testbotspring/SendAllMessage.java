package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.bot.TelegramLongPollingEngine;
import ru.wdeath.managerbot.lib.bot.service.UserTelegramService;
import ru.wdeath.managerbot.lib.db.UserTelegramEntity;
import ru.wdeath.programagent.lib.form.actions.FormActionHandler;
import ru.wdeath.programagent.lib.service.FormActionService;

import javax.annotation.PostConstruct;
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
        return "Рассылка сообщений всем";
    }

    @Override
    public String getType() {
        return "tg-message";
    }

    @Override
    public String getText() {
        return "null";
    }

    @Override
    public void processingText(String text) {
        log.info("Start replay messages");
        Thread thread = new Thread(() -> {
            log.info("Start.......");
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(text);

            List<UserTelegramEntity> all = userTelegramService.findAll();
            all.forEach(userTelegramEntity -> {
                sendMessage.setChatId(userTelegramEntity.getIdChatTelegram());
                try {
                    engine.execute(sendMessage);
                } catch (TelegramApiException e) {
                    log.error("UserTelegram send message error, " + userTelegramEntity, e);
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
