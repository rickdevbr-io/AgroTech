package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestEmailService {

    @Mock
    public JavaMailSender mockJavaMailSender = Mockito.mock(JavaMailSender.class);

    @Test
    public void MustSendMail() {
        EmailService emailService = new EmailService(mockJavaMailSender);

        doNothing().when(mockJavaMailSender).send(any(SimpleMailMessage.class));

        emailService.sendMail("@email", "assunto", "mensagem");

        verify(mockJavaMailSender, atMostOnce()).send(any(SimpleMailMessage.class));
    }
}
