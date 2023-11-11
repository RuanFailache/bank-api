package dev.bank.api.modules.account.infra.docs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Routes related to authentication requests")
public interface AuthenticationControllerDocs {
    @Operation(summary = "Send a validation code to provided email")
    @ApiResponse(responseCode = "201", description = "Email sent successfully")
    @ApiResponse(responseCode = "400", description = "Provided DTO is invalid")
    ResponseEntity<Object> postAuthSend();

    @Operation(summary = "Validate provided code and return a new session")
    @ApiResponse(responseCode = "201", description = "Account authenticated successfully")
    @ApiResponse(responseCode = "400", description = "Provided DTO is invalid")
    ResponseEntity<Object> postAuthValidate();
}
