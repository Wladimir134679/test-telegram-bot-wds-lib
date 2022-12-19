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
public class Test2TableHandler implements TableHandler {

    private final TableHandlerService handlerService;

    @Override
    public String name() {
        return "test2";
    }

    @Override
    public String title() {
        return "Для второй";
    }

    @Override
    public String description() {
        return "Тут описание на одном языке";
    }

    @Override
    public String[] headers() {
        return new String[]{"ID", "UUID"};
    }

    @Override
    public Object[][] rows() {
        int len = 100;
        Object[][] rows = new Object[len][];
        for (int i = 0; i < len; i++) {
            rows[i] = new Object[2];
            rows[i][0] = i;
            rows[i][1] = RandomString.make(10);
        }
        return rows;
    }

    @PostConstruct
    public void init(){
        handlerService.registration(this);
    }
}
