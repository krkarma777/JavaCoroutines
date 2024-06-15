package com.coroutines;

import java.util.concurrent.CompletableFuture;

public abstract class AsyncCoroutine {
    private CoroutineState state = CoroutineState.NEW;
    private CompletableFuture<Void> taskFuture;

    protected abstract CompletableFuture<Void> run();

    public void start() {
        if (state == CoroutineState.NEW || state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            taskFuture = run().thenRun(() -> state = CoroutineState.STOPPED);
        }
    }

    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            // Logic to pause the coroutine (may involve complex state management)
        }
    }

    public void stop() {
        state = CoroutineState.STOPPED;
        if (taskFuture != null) {
            taskFuture.cancel(true);
        }
    }

    public CoroutineState getState() {
        return state;
    }
}
