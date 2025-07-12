//package org.desafio.picpay.client;
//
//import jakarta.ws.rs.GET;
//import jakarta.ws.rs.POST;
//import jakarta.ws.rs.Path;
//import jakarta.ws.rs.Produces;
//import jakarta.ws.rs.core.MediaType;
//import org.desafio.picpay.entity.NotifyClientEntity;
//import org.desafio.picpay.entity.TransactionClientResponse;
//import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
//
//@Produces(MediaType.APPLICATION_JSON)
//@RegisterRestClient(baseUri = "${client.base-uri-service.url}")
//public interface MockiClient {
//
//    @GET
//    @Path("${client.authorization-service.url}")
//    TransactionClientResponse authorize();
//
//    @POST
//    @Path("${client.notification-service.url}")
//    NotifyClientEntity notify(NotifyClientEntity notifyClient);
//}
