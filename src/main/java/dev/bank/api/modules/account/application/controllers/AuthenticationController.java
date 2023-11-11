package dev.bank.api.modules.account.application.controllers;

import dev.bank.api.modules.account.infra.docs.AuthenticationControllerDocs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
public class AuthenticationController implements AuthenticationControllerDocs {
    @Override
    @PostMapping("send")
    public ResponseEntity<Object> postAuthSend() {
        return null;
    }

    @Override
    @PostMapping("validate")
    public ResponseEntity<Object> postAuthValidate() {
        return null;
    }
}
