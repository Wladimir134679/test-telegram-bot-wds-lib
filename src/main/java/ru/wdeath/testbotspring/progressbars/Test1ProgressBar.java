package ru.wdeath.testbotspring.progressbars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.progressbar.ProgressBarHandler;
import ru.wdeath.programagent.lib.service.ProgressBarService;

import jakarta.annotation.PostConstruct;

@Component
public class Test1ProgressBar implements ProgressBarHandler {
    @Override
    public String name() {
        return "p1";
    }

    @Override
    public String description() {
        return "Проверка случайности на прогрессе";
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
