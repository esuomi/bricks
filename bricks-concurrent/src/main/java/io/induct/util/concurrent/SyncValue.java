package io.induct.util.concurrent;

import java.util.concurrent.CountDownLatch;

/**
 * Async-to-sync value helper. Also can be considered to be a single-item blocking producer-consumer abstraction.
 *
 * @since 15.2.2015
 */
public class SyncValue<V> {

    private volatile V value;

    private final CountDownLatch syncLatch = new CountDownLatch(1);

    /**
     * Provide any value for the consumer(s).
     *
     * @param value Value to publish. Any value is accepted.
     * @see SyncValue#get()
     */
    public void push(V value) {
        try {
            this.value = value;
        } finally {
            syncLatch.countDown();
        }
    }

    /**
     * Get the value. This method will block until value is being made available with {@link #push(Object)} or the underlying
     * latch is interrupted. There is no timeout involved making this method potentially hazardous if value is never
     * provided.
     *
     * @return Value produced by calling {@link #push(Object)}
     * @throws io.induct.util.concurrent.HaltedException Thrown if value cannot be acquired.
     */
    public V get() {
        try {
            syncLatch.await();
        } catch (InterruptedException e) {
            throw new HaltedException("Could not acquire value", e);
        }
        return value;
    }
}
