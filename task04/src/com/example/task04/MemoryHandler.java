package com.example.task04;

import java.util.ArrayList;
import java.util.List;

public class MemoryHandler implements MessageHandler {
    private final List<String> buffer = new ArrayList<>();
    private final int bufferSize;
    private final MessageHandler delegate;

    public MemoryHandler(int bufferSize, MessageHandler delegate) {
        this.bufferSize = bufferSize;
        this.delegate = delegate;
    }

    @Override
    public void handle(String message) {
        buffer.add(message);
        if (buffer.size() >= bufferSize) {
            flush();
        }
    }

    public void flush() {
        for (String msg : buffer) {
            delegate.handle(msg);
        }
        buffer.clear();
    }
}