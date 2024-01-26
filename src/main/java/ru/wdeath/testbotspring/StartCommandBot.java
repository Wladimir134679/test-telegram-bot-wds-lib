package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.programagent.lib.model.NewNotificationModel;
import ru.wdeath.programagent.lib.service.BackClientService;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;

@CommandNames({"/start"})
@Component
@RequiredArgsConstructor
public class StartCommandBot {

    public final BackClientService backClientService;

    @CommandFirst
    public void response(TelegramLongPollingEngine engine, @ParamName("chatId") Long chatId){
        var send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        send.setText("И тебе привет");

        backClientService.newNotification("Запуск бота", "Пользователь " + chatId + " запустил бота", NewNotificationModel.Priority.LOW);

        try {
            engine.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
