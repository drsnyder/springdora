package com.wikia.api;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.wikia.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import java.util.concurrent.TimeUnit;

@ComponentScan
@EnableAutoConfiguration
public class Application {

    public static void main(String[] args) {
        startReporter();
        SpringApplication.run(Application.class, args);
    }

    static void startReporter() {
        ConsoleReporter reporter = ConsoleReporter.forRegistry(Config.getInstance().metricRegistry)
                .convertRatesTo(TimeUnit.SECONDS)
                .convertDurationsTo(TimeUnit.MILLISECONDS)
                .build();
        reporter.start(10, TimeUnit.SECONDS);

    }
}
