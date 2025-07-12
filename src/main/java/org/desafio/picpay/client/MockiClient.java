package org.desafio.picpay.client;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.desafio.picpay.entity.client.TransactionClientResponse;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Produces(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "mocki-client")
public interface MockiClient {

    @GET
    @Path("f91dbbf0-0deb-4d48-b922-76fc8e697207")
    TransactionClientResponse authorize();

    @GET
    @Path("a84699c7-9bd9-4eea-aa64-06146178e0cf")
    void notifyClient();
}
