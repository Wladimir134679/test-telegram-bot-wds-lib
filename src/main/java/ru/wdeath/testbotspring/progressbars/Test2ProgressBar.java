package ru.wdeath.testbotspring.progressbars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.progressbar.ProgressBarHandler;
import ru.wdeath.programagent.lib.service.ProgressBarService;

import javax.annotation.PostConstruct;

@Component
public class Test2ProgressBar implements ProgressBarHandler {
    @Override
    public String name() {
        return "p2";
    }

    @Override
    public String description() {
        return "Проверка случайности на выключенном баре";
    }

    @Override
    public boolean isProcessing() {
        return false;
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
