package ru.wdeath.testbotspring;

import lombok.RequiredArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.stereotype.Service;
import ru.wdeath.programagent.lib.service.TableHandlerService;
import ru.wdeath.programagent.lib.tables.TableHandler;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OprosTableHandler implements TableHandler {

    private final TableHandlerService handlerService;

    @Override
    public String name() {
        return "pros";
    }

    @Override
    public String title() {
        return "Опрос";
    }

    @Override
    public String description() {
        return "Люди прошедшие опрос";
    }

    @Override
    public String[] headers() {
        return new String[]{"ID", "Name", "telegramId", "Full name"};
    }

    @Override
    public Object[][] rows() {
        int len = 100;
        Object[][] rows = new Object[len][];
        for (int i = 0; i < len; i++) {
            rows[i] = new Object[4];
            rows[i][0] = i;
            rows[i][1] = RandomString.make(10);
            rows[i][2] = (long)(Math.random() * 1000000L);
            rows[i][3] = RandomString.make(15);
        }
        return rows;
    }

    @PostConstruct
    public void init(){
        handlerService.registration(this);
    }
}
