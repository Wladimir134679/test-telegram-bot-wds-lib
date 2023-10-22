package ru.wdeath.testbotspring;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.annotations.CommandFirst;
import ru.wdeath.telegram.bot.starter.annotations.CommandNames;
import ru.wdeath.telegram.bot.starter.annotations.ParamName;

@CommandNames({"/start"})
@Component
public class STartCommandBot {

    @CommandFirst
    public void response(TelegramLongPollingEngine engine, @ParamName("chatId") Long chatId){
        var send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        send.setText("И тебе привет");

        try {
            engine.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
