package com.coroutines;

public class TestCoroutine extends Coroutine {
    @Override
    protected void run() {
        System.out.println("Coroutine started");
        pauseExecution();
        System.out.println("Coroutine resumed");
        pauseExecution();
        System.out.println("Coroutine finished");
    }

    public static void main(String[] args) {
        TestCoroutine coroutine = new TestCoroutine();
        CoroutineManager manager = CoroutineManager.getInstance();

        manager.createCoroutine(coroutine);
        manager.startCoroutine(coroutine);

        // Simulate the manager running other tasks and then resuming the coroutine
        manager.runNext(); // This will resume the coroutine
        manager.runNext(); // This will finish the coroutine
    }
}
