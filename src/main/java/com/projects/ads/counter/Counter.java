package com.projects.ads.counter;

public class Counter {
    private int count;

    public Counter() {
        this.count = 0;
    }

    public Counter(int init) {
        count = init;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Counter{" +
                "count=" + count +
                '}';
    }
}
