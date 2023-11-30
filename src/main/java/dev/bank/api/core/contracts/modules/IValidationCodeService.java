package dev.bank.api.core.contracts.modules;

import dev.bank.api.modules.session.Session;
import dev.bank.api.modules.validationcode.ValidationCode;

public interface IValidationCodeService {
    ValidationCode sendValidationCode(String email);
    Session validateAuthenticationCode(String idValidationRequest, String code);
}
