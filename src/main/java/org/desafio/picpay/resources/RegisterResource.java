package org.desafio.picpay.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafio.picpay.dto.UserRequestDTO;
import org.desafio.picpay.exception.CnpjNotFoundException;
import org.desafio.picpay.exception.InvalidUserTypeException;
import org.desafio.picpay.service.RegisterService;

import java.util.Map;

@Path("/register")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterResource {

    @Inject
    RegisterService registerService;

    @GET
    public Response get() {
        return Response.ok(registerService.getAll()).build();
    }

    @POST
    @Transactional
    public Response create(@NotNull @Valid UserRequestDTO requestDTO) {
        try {
            return Response.status(Response.Status.CREATED).entity(registerService.register(requestDTO)).build();
        } catch (CnpjNotFoundException | InvalidUserTypeException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", e.getMessage())).build();
        }
    }
}
