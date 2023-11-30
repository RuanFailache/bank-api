package dev.bank.api.core.docs;

import dev.bank.api.core.exceptions.HttpRequestException;
import dev.bank.api.modules.session.dto.SessionCredentialsResponseDto;
import dev.bank.api.modules.validationcode.dto.SendValidationCodeRequestDto;
import dev.bank.api.modules.validationcode.dto.SentValidationCodeResponseDto;
import dev.bank.api.modules.validationcode.dto.ValidateCodeRequestDto;
import dev.bank.api.shared.dtos.HttpRequestExceptionDto;
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
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HttpRequestExceptionDto.class)
                            )
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
                                    schema = @Schema(implementation = SessionCredentialsResponseDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Provided DTO is invalid",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HttpRequestExceptionDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Validation code is incorrect",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HttpRequestExceptionDto.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Validation request not found",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = HttpRequestExceptionDto.class)
                            )
                    )
            }
    )
    ResponseEntity<SessionCredentialsResponseDto> postAuthValidate(ValidateCodeRequestDto requestBody) throws HttpRequestException;
}
