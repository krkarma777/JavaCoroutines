package com.coroutines;

import java.util.concurrent.CompletableFuture;

public class TestAsyncCoroutine extends AsyncCoroutine {
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

    public static void main(String[] args) {
        TestAsyncCoroutine coroutine = new TestAsyncCoroutine();
        coroutine.start();
        // Add more logic to manage async coroutines if needed
    }
}
