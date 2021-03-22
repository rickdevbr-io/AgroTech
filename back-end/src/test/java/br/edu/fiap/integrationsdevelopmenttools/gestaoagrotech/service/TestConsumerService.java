package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestConsumerService {

    @Mock
    EmailService mockEmailService = Mockito.mock(EmailService.class);

    @Test
    public void mustConsumeMessage() throws IOException, MessagingException {
        ConsumerService consumerService = new ConsumerService(mockEmailService);

        doNothing().when(mockEmailService).sendMail(anyString(), anyString(), anyString());

        consumerService.ConsumeMessage("texto");

        verify(mockEmailService, atMostOnce()).sendMail(anyString(), anyString(), anyString());
    }
}
