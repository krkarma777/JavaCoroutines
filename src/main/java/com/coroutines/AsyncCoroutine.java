package com.coroutines;

import java.util.concurrent.CompletableFuture;

/**
 * Abstract class representing an asynchronous coroutine, which allows for cooperative multitasking using CompletableFuture.
 * A coroutine can be started, paused, and stopped, with state transitions managed via CompletableFuture.
 */
public abstract class AsyncCoroutine {
    private CoroutineState state = CoroutineState.NEW;
    private CompletableFuture<Void> taskFuture;

    /**
     * The method to be implemented by subclasses to define the coroutine's behavior.
     * This method should return a CompletableFuture representing the asynchronous task.
     *
     * @return a CompletableFuture representing the asynchronous task
     */
    protected abstract CompletableFuture<Void> run();

    /**
     * Starts the coroutine if it is in the NEW or PAUSED state, changing its state to RUNNING.
     * The coroutine's task is executed asynchronously, and its state changes to STOPPED upon completion.
     */
    public void start() {
        if (state == CoroutineState.NEW || state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            taskFuture = run().thenRun(() -> state = CoroutineState.STOPPED);
        }
    }

    /**
     * Pauses the coroutine if it is currently running, changing its state to PAUSED.
     * Note: Actual logic to pause an asynchronous task may involve more complex state management and is not implemented here.
     */
    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            // Logic to pause the coroutine (may involve complex state management)
        }
    }

    /**
     * Stops the coroutine, changing its state to STOPPED.
     * Cancels the asynchronous task if it is running.
     */
    public void stop() {
        state = CoroutineState.STOPPED;
        if (taskFuture != null) {
            taskFuture.cancel(true);
        }
    }

    /**
     * Returns the current state of the coroutine.
     *
     * @return the current state of the coroutine
     */
    public CoroutineState getState() {
        return state;
    }
}
