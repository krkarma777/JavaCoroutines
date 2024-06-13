package com.coroutines;

import java.util.ArrayList;
import java.util.List;

public class CoroutineManager {

    // List to hold all coroutines
    private List<Coroutine> coroutines = new ArrayList<>();

    // Method to create a new coroutine
    public void createCoroutine(Runnable task) {
        Coroutine coroutine = new Coroutine(task);
        coroutines.add(coroutine);
    }

    // Method to start a specific coroutine
    public void startCoroutine(int index) {
        if (index >= 0 && index < coroutines.size()) {
            coroutines.get(index).start();
        }
    }

    // Method to pause a specific coroutine
    public void pauseCoroutine(int index) {
        if (index >= 0 && index < coroutines.size()) {
            coroutines.get(index).pause();
        }
    }

    // Method to resume a specific coroutine
    public void resumeCoroutine(int index) {
        if (index >= 0 && index < coroutines.size()) {
            coroutines.get(index).resume();
        }
    }

    // Method to stop a specific coroutine
    public void stopCoroutine(int index) {
        if (index >= 0 && index < coroutines.size()) {
            coroutines.get(index).stop();
        }
    }

    // Method to get the state of a specific coroutine
    public CoroutineState getCoroutineState(int index) {
        if (index >= 0 && index < coroutines.size()) {
            return coroutines.get(index).getState();
        }
        return null;
    }
}
