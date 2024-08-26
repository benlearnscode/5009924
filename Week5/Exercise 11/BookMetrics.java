package com.bookstoreapi.metrics;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMetrics {

    private final MeterRegistry meterRegistry;

    @Autowired
    public BookMetrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    public void incrementBookCount() {
        meterRegistry.counter("books.count").increment();
    }
}
