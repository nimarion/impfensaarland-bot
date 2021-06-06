package de.nmarion.impfbot;

import java.io.File;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;

public class Telegram {

    private final TelegramBot telegramBot;
    private final String chatId;

    public Telegram(final String key, final String chatId){
        telegramBot = new TelegramBot(key);
        this.chatId = chatId;
    }

    public void sendMessage(final String message) {
        telegramBot.execute(new SendMessage(chatId, message));
    }

    public void sendImage(final File file, final String message){
        telegramBot.execute(new SendPhoto(chatId, file).caption(message));
    }
    
}
