package dev.bank.api.core.contracts.shared;

public interface IMailSenderService {
    void sendMail(String to, String subject, String content);
}
