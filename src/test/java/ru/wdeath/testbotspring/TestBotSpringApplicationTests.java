package ru.wdeath.testbotspring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.wdeath.managerbot.lib.charts.ChartTimestampMessage;
import ru.wdeath.programagent.lib.model.ChartTimestampModel;

import java.util.List;

@SpringBootTest
class TestBotSpringApplicationTests {

	@Autowired
	private ChartTimestampMessage chartTimestampMessage;

	@Test
	void contextLoads() {
		List<ChartTimestampModel.ChartPointModel> generate = chartTimestampMessage.generate(chartTimestampMessage.getDefaultFromTime(), chartTimestampMessage.getDefaultToTime());
		System.out.println(generate);
	}

}
