package ru.wdeath.testbotspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.progressbar.ProgressBarHandler;
import ru.wdeath.programagent.lib.service.ProgressBarService;

import javax.annotation.PostConstruct;

@Component
public class Test1ProgressBar implements ProgressBarHandler {
    @Override
    public String name() {
        return "Test1";
    }

    @Override
    public String description() {
        return "Проверка случанойсти на прогрессе";
    }

    @Override
    public boolean isProcessing() {
        return true;
    }

    @Override
    public float progress() {
        return (float) Math.random();
    }
    @Autowired
    public ProgressBarService service;

    @PostConstruct
    public void init(){
        service.registration(this);
    }
}
