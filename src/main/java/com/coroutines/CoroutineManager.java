package com.coroutines;

import java.util.ArrayDeque;
import java.util.Queue;

public class CoroutineManager {
    private static CoroutineManager instance;
    private Queue<Coroutine> coroutines = new ArrayDeque<>();

    private CoroutineManager() {
    }

    public static synchronized CoroutineManager getInstance() {
        if (instance == null) {
            instance = new CoroutineManager();
        }
        return instance;
    }

    public void schedule(Coroutine coroutine) {
        coroutines.offer(coroutine);
    }

    public void runNext() {
        if (!coroutines.isEmpty()) {
            Coroutine coroutine = coroutines.poll();
            coroutine.start();
        }
    }
}