package de.nmarion.impfbot.alert;

import java.io.File;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;

public class TelegramAlert implements Alert {

    private final TelegramBot telegramBot;
    private final String chatId;

    public TelegramAlert(final String key, final String chatId) {
        telegramBot = new TelegramBot(key);
        this.chatId = chatId;
    }

    @Override
    public void sendMessage(String message, File file) {
        telegramBot.execute(new SendPhoto(chatId, file).caption(message));
    }

    @Override
    public void sendMessage(String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

}
