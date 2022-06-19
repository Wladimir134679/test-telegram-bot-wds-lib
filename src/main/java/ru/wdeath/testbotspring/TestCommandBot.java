package ru.wdeath.testbotspring;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.bot.TelegramLongPollingEngine;
import ru.wdeath.managerbot.lib.bot.annotations.ParamName;
import ru.wdeath.managerbot.lib.bot.annotations.TextCommandFirst;
import ru.wdeath.managerbot.lib.bot.annotations.TextCommandNames;
import ru.wdeath.managerbot.lib.bot.annotations.TextCommandOther;
import ru.wdeath.managerbot.lib.bot.session.UserBotSession;

import java.util.UUID;

@TextCommandNames("/test")
@Service
public class TestCommandBot {

    @TextCommandFirst
    public void test(TelegramLongPollingEngine engine, Message message, @ParamName("chatId") Long chatId, UserBotSession userBotSession){
        var send = new SendMessage();
        send.setChatId(String.valueOf(chatId));
        userBotSession.setData("Tut data: " + UUID.randomUUID().toString());
        send.setText("тест пройден)");

        try {
            engine.execute(send);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @TextCommandOther
    public void other(TelegramLongPollingEngine engine, Message message, UserBotSession userBotSession, @ParamName("chatId") Long chatId){
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
