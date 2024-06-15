package com.coroutines;

import java.util.ArrayDeque;
import java.util.Queue;

public abstract class Coroutine {
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
            throw new CoroutineSuspendException();
        }
    }

    public void start() {
        if (state == CoroutineState.NEW || state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            try {
                while (!tasks.isEmpty()) {
                    tasks.poll().run();
                }
            } catch (CoroutineSuspendException e) {
                // Coroutine suspended
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

    private static class CoroutineSuspendException extends RuntimeException {
    }
}
