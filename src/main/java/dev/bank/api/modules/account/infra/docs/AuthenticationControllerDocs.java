package dev.bank.api.modules.account.infra.docs;

import dev.bank.api.modules.account.application.dtos.CredentialsResponseDto;
import dev.bank.api.modules.account.application.dtos.SendValidationCodeRequestDto;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import dev.bank.api.modules.account.application.dtos.ValidateCodeRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(name = "Routes related to authentication requests")
public interface AuthenticationControllerDocs {
    @Operation(
            summary = "Send validation code to email",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Email sent successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = SentValidationCodeResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided DTO is invalid",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<SentValidationCodeResponseDto> postAuthSend(SendValidationCodeRequestDto requestBody);

    @Operation(
            summary = "Validate authentication code",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Account authenticated successfully",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = CredentialsResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided DTO is invalid",
                            content = @Content()
                    )
            }
    )
    ResponseEntity<CredentialsResponseDto> postAuthValidate(ValidateCodeRequestDto requestBody);
}
