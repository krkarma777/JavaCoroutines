package com.coroutines;

/**
 * Class representing a test implementation of a coroutine.
 * Demonstrates the usage of Coroutine with simple state transitions.
 */
public class TestCoroutine extends Coroutine {
    /**
     * Defines the behavior of the coroutine.
     * Prints messages and suspends execution to simulate cooperative multitasking.
     */
    @Override
    protected void run() {
        System.out.println("Coroutine started");
        suspend();
        System.out.println("Coroutine resumed");
        suspend();
        System.out.println("Coroutine finished");
    }

    /**
     * Main method to create and manage the test coroutine.
     *
     * @param args command-line arguments
     */
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
