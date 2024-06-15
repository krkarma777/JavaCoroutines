package com.coroutines;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Coroutine {
    private static final Logger logger = Logger.getLogger(Coroutine.class.getName());
    private CoroutineState state = CoroutineState.NEW;
    private Queue<Runnable> tasks = new ArrayDeque<>();

    public Coroutine() {
        tasks.offer(this::run);
    }

    protected abstract void run();

    protected void suspend() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            CoroutineManager.getInstance().schedule(this);
            logger.log(Level.INFO, "Coroutine suspended: {0}", this);
            throw new CoroutineSuspendException();
        }
    }

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

    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            logger.log(Level.INFO, "Coroutine paused: {0}", this);
        }
    }

    public void stop() {
        state = CoroutineState.STOPPED;
        tasks.clear();
        logger.log(Level.INFO, "Coroutine stopped: {0}", this);
    }

    public CoroutineState getState() {
        return state;
    }

    private static class CoroutineSuspendException extends RuntimeException {
    }
}
