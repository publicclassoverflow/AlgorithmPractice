package Q.Recursion.Easy.Fibonacci;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FibonacciTest {
    static Fibonacci instance;

    @BeforeAll
    static void setInstance() {
        instance = new Fibonacci();
    }

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void testNegative() {
        long result = instance.fibonacci(-1);
        assertEquals(result, Integer.MIN_VALUE);
    }

    @Test
    void testZero() {
        long result = instance.fibonacci(0);
        assertEquals(result, 0);
    }

    @Test
    void testFirst() {
        long result = instance.fibonacci(1);
        assertEquals(result, 1);
    }

    @Test
    void testSecond() {
        long result = instance.fibonacci(2);
        assertEquals(result, 1);
    }

    @Test
    void testThird() {
        long result = instance.fibonacci(3);
        assertEquals(result, 2);
    }

    @Test
    void testSixth() {
        long result = instance.fibonacci(6);
        assertEquals(result, 8);
    }
}