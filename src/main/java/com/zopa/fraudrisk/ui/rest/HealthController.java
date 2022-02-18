package com.zopa.fraudrisk.ui.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/health")
public class HealthController {

    @GET
    @Path("/liveness")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLiveness() {
        return "ok";
    }

    @GET
    @Path("/readiness")
    @Produces(MediaType.TEXT_PLAIN)
    public String getReadiness() {
        return null; // TODO: should return OK
    }
}
