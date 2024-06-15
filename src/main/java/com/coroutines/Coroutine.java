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

    protected void yield() {
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
                Objects.requireNonNull(tasks.poll()).run();
            } catch (CoroutineYieldException e) {
                // Coroutine yielded
            }
        }
    }

    public CoroutineState getState() {
        return state;
    }

    private static class CoroutineYieldException extends RuntimeException {
    }
}