package ru.wdeath.testbotspring.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ru.wdeath.programagent.lib.form.actions.FormActionHandler;
import ru.wdeath.programagent.lib.service.FormActionService;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class EditOpros implements FormActionHandler {

    private final static Gson GSON = new GsonBuilder().create();

    private final FormActionService formActionService;

    @Override
    public String getTitle() {
        return "Редактирование опроса";
    }

    @Override
    public String getName() {
        return "req-opros";
    }

    @Override
    public String getDescription() {
        return "Данные об опросе в формате JSON";
    }

    @Override
    public String getType() {
        return "tg-message";
    }

    @Override
    public String getText() {
        return new JSONObject(GSON.toJson(new MyData())).toString(4);
    }

    @Override
    public void processingText(String text) {

    }
    @PostConstruct
    public void init(){
        log.info("Start init form action");
        formActionService.registration(this);
    }

    @Data
    public static class MyData{

        private String title = "Заголовок";
        private String[] questions = new String[]{"Вопрос 1?", "Вопрос 2?"};
        private String[] result = new String[]{"Ответ 1", "Ответ 2"};
    }
}
