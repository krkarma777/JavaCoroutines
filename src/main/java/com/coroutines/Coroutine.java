package com.coroutines;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Abstract class representing a Coroutine, which allows for cooperative multitasking.
 * A coroutine can be started, paused, resumed, and stopped, with state transitions logged for debugging.
 */
public abstract class Coroutine {
    private static final Logger logger = Logger.getLogger(Coroutine.class.getName());
    private CoroutineState state = CoroutineState.NEW;
    private Queue<Runnable> tasks = new ArrayDeque<>();

    /**
     * Constructor initializes the coroutine and schedules the run method.
     */
    public Coroutine() {
        tasks.offer(this::run);
    }

    /**
     * The method to be implemented by subclasses to define the coroutine's behavior.
     */
    protected abstract void run();

    /**
     * Suspends the coroutine if it is currently running, changing its state to PAUSED and rescheduling it.
     * Logs the suspension event.
     */
    protected void suspend() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            CoroutineManager.getInstance().schedule(this);
            logger.log(Level.INFO, "Coroutine suspended: {0}", this);
            throw new CoroutineSuspendException();
        }
    }

    /**
     * Starts the coroutine if it is in the NEW or PAUSED state, changing its state to RUNNING.
     * Executes tasks and logs the start and any suspension events.
     */
    public void start() {
        if (state == CoroutineState.NEW || state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            logger.log(Level.INFO, "Coroutine started: {0}", this);
            try {
                while (!tasks.isEmpty()) {
                    Objects.requireNonNull(tasks.poll()).run();
                }
            } catch (CoroutineSuspendException e) {
                logger.log(Level.INFO, "Coroutine suspended: {0}", this);
            } catch (Exception e) {
                logger.log(Level.SEVERE, "Error in coroutine: {0}", e.getMessage());
                stop();
            }
        }
    }

    /**
     * Pauses the coroutine if it is currently running, changing its state to PAUSED.
     * Logs the pause event.
     */
    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            logger.log(Level.INFO, "Coroutine paused: {0}", this);
        }
    }

    /**
     * Stops the coroutine, changing its state to STOPPED and clearing any remaining tasks.
     * Logs the stop event.
     */
    public void stop() {
        state = CoroutineState.STOPPED;
        tasks.clear();
        logger.log(Level.INFO, "Coroutine stopped: {0}", this);
    }

    /**
     * Returns the current state of the coroutine.
     *
     * @return the current state of the coroutine
     */
    public CoroutineState getState() {
        return state;
    }

    /**
     * Exception thrown when a coroutine is suspended.
     */
    private static class CoroutineSuspendException extends RuntimeException {
    }
}
