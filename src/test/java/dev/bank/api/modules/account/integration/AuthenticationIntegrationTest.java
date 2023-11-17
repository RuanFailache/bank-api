package dev.bank.api.modules.account.integration;

import dev.bank.api.shared.protocols.IntegrationTest;
import dev.bank.api.modules.account.application.dtos.SendValidationCodeRequestDto;
import dev.bank.api.modules.account.application.dtos.ValidateCodeRequestDto;
import dev.bank.api.modules.account.application.services.AuthenticationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class AuthenticationIntegrationTest extends IntegrationTest {
    @Autowired
    private JacksonTester<SendValidationCodeRequestDto> sendValidationCodeRequestDtoJson;

    @Autowired
    private JacksonTester<ValidateCodeRequestDto> validateCodeRequestDtoJson;

    @MockBean
    private AuthenticationService authenticationService;

    @Test
    @DisplayName("Should ensure '/auth/send' returns HttpStatus 400 on blank email")
    public void testPostAuthSend_When_EmailIsBlank() throws Exception {
        var requestBody = new SendValidationCodeRequestDto(
                EMPTY_STRING
        );

        var requestBodyAsJson = sendValidationCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/send", requestBodyAsJson);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should ensure '/auth/send' returns HttpStatus 400 on invalid email")
    public void testPostAuthSend_When_EmailIsInvalid() throws Exception {
        var requestBody = new SendValidationCodeRequestDto(
                faker.lorem().word()
        );

        var requestBodyAsJson = sendValidationCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/send", requestBodyAsJson);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should ensure '/auth/send' returns HttpStatus 201 on success")
    public void testPostAuthSend() throws Exception {
        var requestBody = new SendValidationCodeRequestDto(
                faker.internet().emailAddress()
        );

        var requestBodyAsJson = sendValidationCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/send", requestBodyAsJson);

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should ensure '/auth/validate' returns HttpStatus 400 on blank idValidationRequest")
    public void testPostAuthValidate_When_IdValidationRequestIsBlank() throws Exception {
        var requestBody = new ValidateCodeRequestDto(
                faker.lorem().word(),
                EMPTY_STRING
        );

        var requestBodyAsJson = validateCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/validate", requestBodyAsJson);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should ensure '/auth/validate' returns HttpStatus 400 on invalid code")
    public void testPostAuthValidate_When_CodeIsBlank() throws Exception {
        var requestBody = new ValidateCodeRequestDto(
                faker.lorem().word(),
                EMPTY_STRING
        );

        var requestBodyAsJson = validateCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/validate", requestBodyAsJson);

        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    @DisplayName("Should ensure '/auth/validate' returns HttpStatus 201 with correct body")
    public void testPostAuthValidate_When_ValidationRequestNotFound() throws Exception {
        var requestBody = new ValidateCodeRequestDto(
                UUID.randomUUID().toString(),
                faker.number().digits(6)
        );

        var requestBodyAsJson = validateCodeRequestDtoJson.write(requestBody).getJson();

        var response = post("/auth/validate", requestBodyAsJson);

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }
}
