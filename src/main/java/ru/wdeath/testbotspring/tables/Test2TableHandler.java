package ru.wdeath.testbotspring.tables;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import ru.wdeath.programagent.lib.service.TableHandlerService;
import ru.wdeath.programagent.lib.tables.TableHandler;

import jakarta.annotation.PostConstruct;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class Test2TableHandler implements TableHandler {

    private final TableHandlerService handlerService;

    @Override
    public String name() {
        return "test2";
    }

    @Override
    public String title() {
        return "Результаты опроса";
    }

    @Override
    public String description() {
        return "Пользователи которые завершили опрос, видны здесь. Данные сгенерированы случайно";
    }

    @Override
    public String[] headers() {
        return new String[]{"ID", "UUID", "Средний балл", "Время прохождения(секунды)"};
    }

    @Override
    public Object[][] rows() {
        int len = 100;
        Object[][] rows = new Object[len][];
        for (int i = 0; i < len; i++) {
            rows[i] = new Object[4];
            rows[i][0] = i;
            rows[i][1] = RandomStringUtils.random(10);
            rows[i][2] = (int)(Math.random() * 100);
            rows[i][3] = (int)(Math.random() * 500) + 10;
        }
        return rows;
    }

    @PostConstruct
    public void init(){
        handlerService.registration(this);
    }
}
