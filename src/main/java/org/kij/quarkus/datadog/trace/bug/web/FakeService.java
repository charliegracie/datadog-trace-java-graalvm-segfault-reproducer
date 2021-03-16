package org.kij.quarkus.datadog.trace.bug.web;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class FakeService
{
    private static final Logger LOGGER = LoggerFactory.getLogger(FakeService.class.getName());

    @Traced(operationName = "fake service")
    public void justLog() {

        LOGGER.info("Just a log");
    }

    @Traced(operationName = "dog barking")
    public void bark() {

        LOGGER.info("Woof");
    }
}
