package ru.wdeath.testbotspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.wdeath.programagent.lib.chart.ChartHandler;
import ru.wdeath.programagent.lib.model.ChartTimestampModel;
import ru.wdeath.programagent.lib.service.ChartTimestampHandlerService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TestCharts implements ChartHandler {

    @Autowired
    private ChartTimestampHandlerService timestampHandlerService;

    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getNameDisplay() {
        return "Name Display";
    }

    @Override
    public String getDescription() {
        return "Desc";
    }

    @Override
    public LocalDateTime getDefaultFromTime() {
        return LocalDateTime.now().minusDays(1);
    }

    @Override
    public LocalDateTime getDefaultToTime() {
        return LocalDateTime.now();
    }

    @Override
    public List<ChartTimestampModel.ChartPointModel> generate(ChartTimestampModel.GroupType groupType,
                                                              LocalDateTime from,
                                                              LocalDateTime to) {
        List<ChartTimestampModel.ChartPointModel> models = new ArrayList<>();
        LocalDateTime time = from.plusMinutes(1);
        while (time.isBefore(to)) {
            ChartTimestampModel.ChartPointModel model = new ChartTimestampModel.ChartPointModel();
            model.setValue((long) (Math.random() * 1000) + 100);
            model.setTime(time);
            models.add(model);
            time = time.plusMinutes(30);
        }
        return models;
    }

    @PostConstruct
    public void init() {
        timestampHandlerService.registration(this);
    }
}
