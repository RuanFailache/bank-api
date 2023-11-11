package dev.bank.api.modules.account.application.controllers;

import dev.bank.api.modules.account.application.dtos.LoggedAccountResponseDto;
import dev.bank.api.modules.account.application.dtos.SendValidationCodeRequestDto;
import dev.bank.api.modules.account.application.dtos.ValidateCodeRequestDto;
import dev.bank.api.modules.account.infra.docs.AuthenticationControllerDocs;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements AuthenticationControllerDocs {
    @Override
    @PostMapping("send")
    public ResponseEntity<LoggedAccountResponseDto> postAuthSend(
            @RequestBody
            @Valid SendValidationCodeRequestDto requestBody
    ) {
        return null;
    }

    @Override
    @PostMapping("validate")
    public ResponseEntity<LoggedAccountResponseDto> postAuthValidate(
            @RequestBody
            @Valid ValidateCodeRequestDto requestBody
    ) {
        return null;
    }
}
