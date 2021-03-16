package org.kij.quarkus.datadog.trace.bug.web;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.smallrye.mutiny.Uni;

@Path("/demo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReproducerController
{

    private static final Logger LOGGER =
            LoggerFactory.getLogger(ReproducerController.class.getName());

    @Inject
    FakeService fakeService;

    @GET
    @Path("/request1")
    public Uni<Void> request1() {

        fakeService.justLog();

        LOGGER.info("request1 in between");

        fakeService.justLog();

        return Uni.createFrom()
                .voidItem();
    }

    @GET
    @Path("/request2")
    public Uni<Void> request2() {

        fakeService.bark();

        LOGGER.info("Hey dog !");

        return Uni.createFrom()
                .voidItem();
    }

}
