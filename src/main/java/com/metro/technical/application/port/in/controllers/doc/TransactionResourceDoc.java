package com.metro.technical.application.port.in.controllers.doc;

import com.metro.technical.application.port.in.controllers.models.TransactionRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.ws.rs.core.Response;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TransactionResourceDoc {

    @Operation(
            summary = "Creation",
            description = "Handling transactions",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Created successfully",
                            content = @Content(schema = @Schema(implementation = String.class)))
            })
    Response createTransaction(TransactionRequest transactionRequest);
}
