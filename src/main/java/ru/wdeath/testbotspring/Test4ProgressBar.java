package ru.wdeath.testbotspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.progressbar.ProgressBarHandler;
import ru.wdeath.programagent.lib.service.ProgressBarService;

import javax.annotation.PostConstruct;

@Component
public class Test4ProgressBar implements ProgressBarHandler {

    // =============== Регистрация процесса в адвижке
    @Autowired
    public ProgressBarService service;

    @PostConstruct
    public void init(){
        service.registration(this);
    }


    // ==========================================

    @Override
    public String name() {
        return "Test4";
    }

    @Override
    public String description() {
        return "Статичное число";
    }

    @Override
    public boolean isProcessing() {
        return false;
    }

    @Override
    public float progress() {
        return 0.24f;
    }

}
