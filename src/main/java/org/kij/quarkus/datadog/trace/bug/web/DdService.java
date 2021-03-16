package org.kij.quarkus.datadog.trace.bug.web;

import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class DdService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(FakeService.class.getName());

    @Traced(operationName = "dog barking")
    public void bark() {

        LOGGER.info("Woof");
    }
}
