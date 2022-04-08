package ru.example.threads;

import org.junit.jupiter.api.Test;

import java.util.concurrent.Future;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


class ThreadsafeComputerTest {

    @Test
    void compute() throws Exception {
        final ThreadsafeComputer<Integer, Integer> c = new ThreadsafeComputer<>();

        final ComputationMethods cm = spy(new ComputationMethods());

        final Future<Integer> firstCall = c.compute(1, cm::addOne);
        assertEquals(2, firstCall.get());

        final Future<Integer> secondCall = c.compute(1, cm::addTwo);
        assertSame(firstCall, secondCall);
        assertEquals(2, secondCall.get());

        assertEquals(4, c.compute(2, cm::addTwo).get());

        verify(cm, times(1)).addOne(1);
        verify(cm, times(0)).addTwo(1);
        verify(cm, times(1)).addTwo(2);
    }

    public static class ComputationMethods {

        Integer addOne(Integer i) {
            return i + 1;
        }

        Integer addTwo(Integer i) {
            return i + 2;
        }
    }
}