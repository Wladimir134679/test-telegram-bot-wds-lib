package ru.wdeath.testbotspring;

import org.springframework.stereotype.Service;
import ru.wdeath.managerbot.lib.bot.interfaces.SettingTelegramBot;

@Service
public class SettingBot implements SettingTelegramBot {

    @Override
    public boolean isSessionCommand() {
        return false;
    }
}
