package com.xh.demo_lb.prometheus;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;

public class PrometheusMetrics {
    public static Gauge TEST_GAUGE = Gauge.build()
            .name("test1")
            .labelNames("backend")
            .help("test 111")
            .register();

    public static Counter TEST_COUNT = Counter.build()
            .name("test2")
            .labelNames("backend")
            .help("test 222")
            .register();
}
