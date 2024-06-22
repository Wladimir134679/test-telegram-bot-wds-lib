package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.groupadministration.ApproveChatJoinRequest;
import org.telegram.telegrambots.meta.api.objects.ChatJoinRequest;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.chat.Chat;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.wdeath.managerbot.lib.service.UsersUseBotService;
import ru.wdeath.telegram.bot.starter.TelegramLongPollingEngine;
import ru.wdeath.telegram.bot.starter.interfaces.HandlerBotUpdate;

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
            ApproveChatJoinRequest request = ApproveChatJoinRequest.builder()
                    .chatId(chat.getId())
                    .userId(user.getId())
                    .build();
            try {
                Boolean execute = engine.getClient().execute(request);
                log.info("Заявка одобрена: {}", execute);
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
