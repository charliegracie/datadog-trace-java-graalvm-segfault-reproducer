package org.kij.quarkus.datadog.trace.bug.config;

import java.util.Properties;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import datadog.opentracing.DDTracer;
import io.opentracing.util.GlobalTracer;
import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class DDTracerActivation
{

    public void onStartup(@Observes StartupEvent event) {

        final Properties properties = ConfigResolver.resolve();

        // Initialize the tracer using properties
        DDTracer tracer = DDTracer.builder()
                .withProperties(properties)
                .build();

        // Add a scope listener to provides DD trace & span ID on the MDC for logging correlation
        tracer.addScopeListener(new Slf4jMdcInjectingScopeListener());

        // Setup DD tracer implementation
        GlobalTracer.registerIfAbsent(tracer);
        datadog.trace.api.GlobalTracer.registerIfAbsent(tracer);
    }
}
