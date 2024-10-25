package com.example.task01;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Logger {
    public enum Level {
        DEBUG, INFO, WARNING, ERROR
    }

    private static final Map<String, Logger> loggers = new HashMap<>();
    private final String name;
    private Level currentLevel = Level.DEBUG;

    private Logger(String name) {
        this.name = name;
    }

    // через getLogger проходимся по списку loggers, чтобы найти существующий логгер с указанным именем
    // если не нашли - создаём новый и добавляем его в список
    public static Logger getLogger(String name) {
        if (!loggers.containsKey(name)) {
            loggers.put(name, new Logger(name));
        }
        return loggers.get(name);
    }

    public String getName() {
        return name;
    }

    public Level getLevel() {
        return currentLevel;
    }

    public void setLevel(Level level) {
        this.currentLevel = level;
    }

    // для форматирования и вывода сообщения
    private void log(Level level, String message) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String timestamp = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date());
            System.out.println("[" + level + "] " + timestamp + " " + name + " - " + message);
        }
    }

    // для логирования сообщения с форматированием
    private void log(Level level, String template, Object... args) {
        if (level.ordinal() >= currentLevel.ordinal()) {
            String message = String.format(template, args);
            log(level, message);
        }
    }

    // для каждого уровня логирования
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

    // для форматированного логирования
    public void info(String template, Object... args) {
        log(Level.INFO, template, args);
    }

    public void warning(String template, Object... args) {
        log(Level.WARNING, template, args);
    }

    public void error(String template, Object... args) {
        log(Level.ERROR, template, args);
    }

    public void debug(String template, Object... args) {
        log(Level.DEBUG, template, args);
    }
}
