package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.groupadministration.ApproveChatJoinRequest;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.ChatJoinRequest;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.bot.TelegramLongPollingEngine;
import ru.wdeath.managerbot.lib.bot.interfaces.HandlerBotUpdate;
import ru.wdeath.managerbot.lib.bot.service.UsersUseBotService;

@Component
@Slf4j
@RequiredArgsConstructor
public class JointGroupHandler implements HandlerBotUpdate {

    private static final int PRIORITY = 10;

    private final UsersUseBotService usersUseBotService;

    @Override
    public void update(TelegramLongPollingEngine engine, Update update) {
        if(update.hasChatJoinRequest()){
            log.info("Подана заявка на вступление");
            ChatJoinRequest chatJoinRequest = update.getChatJoinRequest();
            User user = chatJoinRequest.getUser();
            Chat chat = chatJoinRequest.getChat();
            log.info("User {} вступил in {}", user.getFirstName(), chat.getFirstName());
            ApproveChatJoinRequest request = new ApproveChatJoinRequest();
            request.setChatId(chat.getId());
            request.setUserId(user.getId());
            try {
                Boolean execute = engine.execute(request);
                log.info("Заявка одобрена: " + execute);
                usersUseBotService.userUser(user, true);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public int priority() {
        return PRIORITY;
    }
}
