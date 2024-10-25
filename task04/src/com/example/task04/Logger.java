package com.example.task04;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Logger {
    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static final List<Logger> loggers = new ArrayList<>();
    private final String name;
    private Level currentLevel = Level.DEBUG;
    private final List<MessageHandler> handlers = new ArrayList<>();

    private Logger(String name) {
        this.name = name;
    }

    public static Logger getLogger(String name) {
        for (Logger logger : loggers) {
            if (logger.name.equals(name)) {
                return logger;
            }
        }
        Logger newLogger = new Logger(name);
        loggers.add(newLogger);
        return newLogger;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void addHandler(MessageHandler handler) {
        handlers.add(handler);
    }

    private void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String formattedMessage = formatMessage(level, message);
            for (MessageHandler handler : handlers) {
                handler.handle(formattedMessage);
            }
        }
    }

    private String formatMessage(Level level, String message) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
        return "[" + level + "] " + timestamp + " " + name + " - " + message;
    }

    public void debug(String message) {
        log(Level.DEBUG, message);
    }

    public void info(String message) {
        log(Level.INFO, message);
    }

    public void warning(String message) {
        log(Level.WARNING, message);
    }

    public void error(String message) {
        log(Level.ERROR, message);
    }
}
