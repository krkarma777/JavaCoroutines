package com.coroutines;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton class that manages coroutines. Provides methods to create, start, pause, stop, schedule, and run coroutines.
 * Logs important state changes and actions for debugging and monitoring purposes.
 */
public class CoroutineManager {
    private static final Logger logger = Logger.getLogger(CoroutineManager.class.getName());
    private static CoroutineManager instance;
    private Queue<Coroutine> coroutines = new ArrayDeque<>();

    /**
     * Private constructor to enforce singleton pattern.
     */
    private CoroutineManager() {
    }

    /**
     * Returns the singleton instance of CoroutineManager.
     *
     * @return the singleton instance of CoroutineManager
     */
    public static synchronized CoroutineManager getInstance() {
        if (instance == null) {
            instance = new CoroutineManager();
        }
        return instance;
    }

    /**
     * Creates a new coroutine and adds it to the queue. Logs the creation of the coroutine.
     *
     * @param coroutine the coroutine to be created and managed
     */
    public void createCoroutine(Coroutine coroutine) {
        coroutines.offer(coroutine);
        logger.log(Level.INFO, "Coroutine created: {0}", coroutine);
    }

    /**
     * Starts the specified coroutine. The coroutine changes its state to RUNNING.
     *
     * @param coroutine the coroutine to be started
     */
    public void startCoroutine(Coroutine coroutine) {
        coroutine.start();
    }

    /**
     * Pauses the specified coroutine. The coroutine changes its state to PAUSED.
     *
     * @param coroutine the coroutine to be paused
     */
    public void pauseCoroutine(Coroutine coroutine) {
        coroutine.pause();
    }

    /**
     * Stops the specified coroutine. The coroutine changes its state to STOPPED and clears its tasks.
     *
     * @param coroutine the coroutine to be stopped
     */
    public void stopCoroutine(Coroutine coroutine) {
        coroutine.stop();
    }

    /**
     * Schedules the specified coroutine to be run later. Only coroutines in the PAUSED state can be scheduled.
     * Logs the scheduling of the coroutine.
     *
     * @param coroutine the coroutine to be scheduled
     */
    public void schedule(Coroutine coroutine) {
        if (coroutine.getState() == CoroutineState.PAUSED) {
            coroutines.offer(coroutine);
            logger.log(Level.INFO, "Coroutine scheduled: {0}", coroutine);
        }
    }

    /**
     * Runs the next coroutine in the queue. Changes its state to RUNNING and logs the resumption of the coroutine.
     */
    public void runNext() {
        if (!coroutines.isEmpty()) {
            Coroutine coroutine = coroutines.poll();
            coroutine.start();
            logger.log(Level.INFO, "Coroutine resumed: {0}", coroutine);
        }
    }
}
