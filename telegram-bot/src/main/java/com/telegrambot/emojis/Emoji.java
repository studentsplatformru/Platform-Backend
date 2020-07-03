package com.telegrambot.emojis;

public enum  Emoji {
    SMILE("\uD83D\uDE04");

    private final String unicode;

    Emoji(String unicode) {
        this.unicode = unicode;
    }

    public String unicode() {
        return unicode;
    }
}
