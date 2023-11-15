package dev.bank.api.modules.account.application.controllers;

import dev.bank.api.core.exceptions.HttpRequestException;
import dev.bank.api.core.exceptions.NotFoundException;
import dev.bank.api.core.exceptions.UnauthorizedException;
import dev.bank.api.modules.account.application.dtos.CredentialsResponseDto;
import dev.bank.api.modules.account.application.dtos.SendValidationCodeRequestDto;
import dev.bank.api.modules.account.application.dtos.SentValidationCodeResponseDto;
import dev.bank.api.modules.account.application.dtos.ValidateCodeRequestDto;
import dev.bank.api.modules.account.application.services.AuthenticationService;
import dev.bank.api.modules.account.infra.docs.AuthenticationControllerDocs;

import jakarta.mail.internet.AddressException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController implements AuthenticationControllerDocs {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("send")
    public ResponseEntity<SentValidationCodeResponseDto> postAuthSend(
            @RequestBody
            @Valid SendValidationCodeRequestDto requestBody
    ) {
        var response = authenticationService.sendValidationCode(
                requestBody.email()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("validate")
    public ResponseEntity<CredentialsResponseDto> postAuthValidate(
            @RequestBody
            @Valid ValidateCodeRequestDto requestBody
    ) throws HttpRequestException {
        var response = authenticationService.validateAuthenticationCode(
                requestBody.idValidationRequest(),
                requestBody.code()
        );
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
