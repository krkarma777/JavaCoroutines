package com.coroutines;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CoroutineManager {
    private static final Logger logger = Logger.getLogger(CoroutineManager.class.getName());
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

    public void createCoroutine(Coroutine coroutine) {
        coroutines.offer(coroutine);
        logger.log(Level.INFO, "Coroutine created: {0}", coroutine);
    }

    public void startCoroutine(Coroutine coroutine) {
        coroutine.start();
    }

    public void pauseCoroutine(Coroutine coroutine) {
        coroutine.pause();
    }

    public void stopCoroutine(Coroutine coroutine) {
        coroutine.stop();
    }

    public void schedule(Coroutine coroutine) {
        if (coroutine.getState() == CoroutineState.PAUSED) {
            coroutines.offer(coroutine);
            logger.log(Level.INFO, "Coroutine scheduled: {0}", coroutine);
        }
    }

    public void runNext() {
        if (!coroutines.isEmpty()) {
            Coroutine coroutine = coroutines.poll();
            coroutine.start();
            logger.log(Level.INFO, "Coroutine resumed: {0}", coroutine);
        }
    }
}
