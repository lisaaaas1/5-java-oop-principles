package com.example.task04;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class RotationFileHandler implements MessageHandler {
    private final String filePrefix;
    private final ChronoUnit rotationUnit;
    private LocalDateTime lastRotationTime;

    public RotationFileHandler(String filePrefix, ChronoUnit rotationUnit) {
        this.filePrefix = filePrefix;
        this.rotationUnit = rotationUnit;
        this.lastRotationTime = LocalDateTime.now();
    }

    private String getCurrentFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HH");
        return filePrefix + "_" + lastRotationTime.format(formatter) + ".log";
    }

    @Override
    public void handle(String message) {
        LocalDateTime now = LocalDateTime.now();
        if (now.isAfter(lastRotationTime.plus(1, rotationUnit))) {
            lastRotationTime = now;
        }

        String fileName = getCurrentFileName();
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
