package de.nmarion.impfbot.alert;

import java.io.File;

public interface Alert {

    void sendMessage(final String message, final File file);
    void sendMessage(final String message);
    
}
