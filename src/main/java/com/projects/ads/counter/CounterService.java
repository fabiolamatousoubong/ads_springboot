package com.projects.ads.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterService {

    private final Counter counter;

    @Autowired
    public CounterService() {
        this.counter = new Counter();
    }

    public Counter getCounter() {
        return this.counter;
    }

    public void clear() {
        this.counter.setCount(0);
    }

    public int incrementAndGet() {
        this.counter.setCount(this.counter.getCount() + 1);
        return this.counter.getCount();
    }
}
