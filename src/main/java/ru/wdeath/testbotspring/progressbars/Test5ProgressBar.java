package ru.wdeath.testbotspring.progressbars;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.progressbar.ProgressBarHandler;
import ru.wdeath.programagent.lib.service.ProgressBarService;

import jakarta.annotation.PostConstruct;

@Component
public class Test5ProgressBar implements ProgressBarHandler {
    @Override
    public String name() {
        return "p5 - Выключенный";
    }

    @Override
    public String description() {
        return "Выключенный полностью";
    }

    @Override
    public boolean isProcessing() {
        return false;
    }

    @Override
    public float progress() {
        return 0;
    }


    @Autowired
    public ProgressBarService service;

    @PostConstruct
    public void init(){
        service.registration(this);
    }
}
