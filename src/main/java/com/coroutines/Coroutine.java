package com.coroutines;

public class Coroutine {
    private Runnable task;
    private CoroutineState state;

    public Coroutine(Runnable task) {
        this.task = task;
        this.state = CoroutineState.NEW;
    }

    public void start() {
        state = CoroutineState.RUNNING;
        // Code to start the coroutine
        new Thread(task).start();
    }

    public void pause() {
        if (state == CoroutineState.RUNNING) {
            state = CoroutineState.PAUSED;
            // Code to pause the coroutine
        }
    }

    public void resume() {
        if (state == CoroutineState.PAUSED) {
            state = CoroutineState.RUNNING;
            // Code to resume the coroutine
        }
    }

    public void stop() {
        state = CoroutineState.STOPPED;
        // Code to stop the coroutine
    }

    public CoroutineState getState() {
        return state;
    }
}