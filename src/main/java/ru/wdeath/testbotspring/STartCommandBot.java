package ru.wdeath.testbotspring;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.bot.TelegramLongPollingEngine;
import ru.wdeath.managerbot.lib.bot.command.annotations.ParamName;
import ru.wdeath.managerbot.lib.bot.command.annotations.TextCommandFirst;
import ru.wdeath.managerbot.lib.bot.command.annotations.TextCommandNames;

@TextCommandNames({"/start"})
@Component
public class STartCommandBot {

    @TextCommandFirst
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
