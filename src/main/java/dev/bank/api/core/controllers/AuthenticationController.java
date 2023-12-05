package dev.bank.api.core.controllers;

import dev.bank.api.core.docs.AuthenticationControllerDocs;
import dev.bank.api.core.exceptions.HttpRequestException;
import dev.bank.api.modules.session.SessionCredentialsResponseDto;
import dev.bank.api.modules.validationcode.ValidationCodeService;
import dev.bank.api.modules.validationcode.SendValidationCodeRequestDto;
import dev.bank.api.modules.validationcode.SentValidationCodeResponseDto;
import dev.bank.api.modules.validationcode.ValidateCodeRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "auth")
public class AuthenticationController implements AuthenticationControllerDocs {
    private final ValidationCodeService validationCodeService;

    public AuthenticationController(ValidationCodeService validationCodeService) {
        this.validationCodeService = validationCodeService;
    }

    @PostMapping("send")
    public ResponseEntity<SentValidationCodeResponseDto> postAuthSend(
            @RequestBody
            @Valid SendValidationCodeRequestDto requestBody
    ) {
        var validationCode = validationCodeService.sendValidationCode(
                requestBody.email()
        );

        var dto = SentValidationCodeResponseDto.from(validationCode);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PostMapping("validate")
    public ResponseEntity<SessionCredentialsResponseDto> postAuthValidate(
            @RequestBody
            @Valid ValidateCodeRequestDto requestBody
    ) throws HttpRequestException {
        var session = validationCodeService.validateAuthenticationCode(
                requestBody.idValidationRequest(),
                requestBody.code()
        );

        var dto = SessionCredentialsResponseDto.from(session);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }
}
