package de.nmarion.impfbot.alert;

import java.io.File;

import club.minnced.discord.webhook.WebhookClient;

public class DiscordAlert implements Alert {

    private final WebhookClient webhookClient;

    public DiscordAlert(final String url) {
        this.webhookClient = WebhookClient.withUrl(
               url);
    }

    @Override
    public void sendMessage(String message, File file) {
        webhookClient.send(file);
        sendMessage(message);
    }

    @Override
    public void sendMessage(String message) {
        webhookClient.send(message);
    }

}
