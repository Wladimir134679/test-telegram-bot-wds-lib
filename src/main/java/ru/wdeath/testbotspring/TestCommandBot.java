package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.programagent.lib.model.NewNotificationModel;
import ru.wdeath.programagent.lib.service.BackClientService;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.CommandOther;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;
import ru.wdeath.telegram.bot.starter.session.UserBotSession;

import java.util.Random;
import java.util.UUID;

@CommandNames("/test")
@Service
@RequiredArgsConstructor
public class TestCommandBot {

    private final BackClientService backClientService;

    @CommandFirst
    public void test(TelegramLongPollingEngine engine, Message message, @ParamName("chatId") Long chatId, UserBotSession userBotSession) {
        var send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        userBotSession.setData("Tut data: " + UUID.randomUUID().toString());
        send.setText("тест пройден)");

        backClientService.newNotification(
                "Тест пользователем " + message.getFrom().getFirstName() + " пройден",
                "Результат теста " + (int) (Math.random() * 100),
                NewNotificationModel.Priority.MEDIUM);

        try {
            engine.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @CommandOther
    public void other(TelegramLongPollingEngine engine, Message message, UserBotSession userBotSession, @ParamName("chatId") Long chatId) {
        var send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        send.setText("Были такие данные: " + userBotSession.getData());

        try {
            engine.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
