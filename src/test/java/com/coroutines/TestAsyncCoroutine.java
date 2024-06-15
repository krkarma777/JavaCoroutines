package com.coroutines;

import java.util.concurrent.CompletableFuture;

/**
 * Class representing a test implementation of an asynchronous coroutine.
 * Demonstrates the usage of AsyncCoroutine with simulated work using sleep.
 */
public class TestAsyncCoroutine extends AsyncCoroutine {
    /**
     * Defines the behavior of the asynchronous coroutine.
     * Simulates work by printing messages and sleeping.
     *
     * @return a CompletableFuture representing the asynchronous task
     */
    @Override
    protected CompletableFuture<Void> run() {
        return CompletableFuture.runAsync(() -> {
            try {
                System.out.println("Async Coroutine started");
                Thread.sleep(1000); // Simulate work
                System.out.println("Async Coroutine resumed");
                Thread.sleep(1000); // Simulate more work
                System.out.println("Async Coroutine finished");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * Main method to start the test asynchronous coroutine.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        TestAsyncCoroutine coroutine = new TestAsyncCoroutine();
        coroutine.start();
        // Add more logic to manage async coroutines if needed
    }
}
