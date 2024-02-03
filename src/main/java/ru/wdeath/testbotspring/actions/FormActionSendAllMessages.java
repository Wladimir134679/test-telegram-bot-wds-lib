package ru.wdeath.testbotspring.actions;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.form.actions.FormActionHandler;
import ru.wdeath.programagent.lib.model.NewNotificationModel;
import ru.wdeath.programagent.lib.service.BackClientService;
import ru.wdeath.programagent.lib.service.FormActionService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class FormActionSendAllMessages implements FormActionHandler {

    private final FormActionService formActionService;
    private final BackClientService backClientService;
    private String lastSend = "";

    @Override
    public String getTitle() {
        return "Создание отчета";
    }

    @Override
    public String getName() {
        return "send-message";
    }

    @Override
    public String getDescription() {
        return "{}";
    }

    @Override
    public String getType() {
        return "message";
    }

    @Override
    public String getText() {
        return lastSend;
    }

    @Override
    public void processingText(String text) {
        log.info("Processing new message: " + text);
        lastSend = text;


        backClientService.newNotification("События формы " + getTitle(), "Было сделано то-то и то-то, вот ваш запрос: " + text, NewNotificationModel.Priority.HIGH);
    }

    @PostConstruct
    public void init(){
        log.info("Start init form action");
        formActionService.registration(this);
    }
}
