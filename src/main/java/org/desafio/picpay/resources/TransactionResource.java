package org.desafio.picpay.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.desafio.picpay.dto.TransactionRequestDTO;
import org.desafio.picpay.exception.InsufficientAmountException;
import org.desafio.picpay.exception.InvalidAmountException;
import org.desafio.picpay.exception.UserTypeTransactionNotAllowed;
import org.desafio.picpay.service.TransactionService;

import java.util.Map;

@Path("/transfer")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransactionResource {

    @Inject
    TransactionService transactionService;

    @POST
    @Transactional
    public Response createTransaction(@NotNull @Valid TransactionRequestDTO transactionRequestDTO) {
        try {
            return Response.ok(transactionService.create(transactionRequestDTO)).build();
        } catch (UserTypeTransactionNotAllowed | InvalidAmountException | InsufficientAmountException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(Map.of("error", e.getMessage())).build();
        }
    }
}
