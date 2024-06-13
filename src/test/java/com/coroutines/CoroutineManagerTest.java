package com.coroutines;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CoroutineManagerTest {
    private CoroutineManager manager;

    @BeforeEach
    public void setUp() {
        manager = new CoroutineManager();
    }

    @Test
    public void testCreateAndStartCoroutine() {
        Runnable task = () -> System.out.println("Running coroutine");
        manager.createCoroutine(task);

        manager.startCoroutine(0);
        assertEquals(CoroutineState.RUNNING, manager.getCoroutineState(0));
    }

    @Test
    public void testPauseAndResumeCoroutine() {
        Runnable task = () -> System.out.println("Running coroutine");
        manager.createCoroutine(task);

        manager.startCoroutine(0);
        manager.pauseCoroutine(0);
        assertEquals(CoroutineState.PAUSED, manager.getCoroutineState(0));

        manager.resumeCoroutine(0);
        assertEquals(CoroutineState.RUNNING, manager.getCoroutineState(0));
    }

    @Test
    public void testStopCoroutine() {
        Runnable task = () -> System.out.println("Running coroutine");
        manager.createCoroutine(task);

        manager.startCoroutine(0);
        manager.stopCoroutine(0);
        assertEquals(CoroutineState.STOPPED, manager.getCoroutineState(0));
    }
}
