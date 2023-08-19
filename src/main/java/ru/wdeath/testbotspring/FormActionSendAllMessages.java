package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.form.actions.FormActionHandler;
import ru.wdeath.programagent.lib.service.FormActionService;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
@Slf4j
public class FormActionSendAllMessages implements FormActionHandler {

    private final FormActionService formActionService;
    private String lastSend = "";

    @Override
    public String getTitle() {
        return "НЕУ";
    }

    @Override
    public String getName() {
        return "send-message";
    }

    @Override
    public String getDescription() {
        return "ВФЦВФЦй";
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
    }

    @PostConstruct
    public void init(){
        log.info("Start init form action");
        formActionService.registration(this);
    }
}