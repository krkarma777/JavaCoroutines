package com.coroutines;

import java.util.ArrayDeque;
import java.util.Objects;
import java.util.Queue;

public abstract class Coroutine {
    private CoroutineState state = CoroutineState.NEW;
    private Queue<Runnable> tasks = new ArrayDeque<>();

    public Coroutine() {
        tasks.offer(this::run);
    }

    protected abstract void run();

    protected void pauseExecution() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            CoroutineManager.getInstance().schedule(this);
            throw new CoroutineYieldException();
        }
    }

    public void start() {
        if (state == CoroutineState.NEW || state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            try {
                while (!tasks.isEmpty()) {
                    Objects.requireNonNull(tasks.poll()).run();
                }
            } catch (CoroutineYieldException e) {
                // Coroutine yielded
            }
        }
    }

    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
        }
    }

    public void stop() {
        state = CoroutineState.STOPPED;
        tasks.clear();
    }

    public CoroutineState getState() {
        return state;
    }

    private static class CoroutineYieldException extends RuntimeException {
    }
}
