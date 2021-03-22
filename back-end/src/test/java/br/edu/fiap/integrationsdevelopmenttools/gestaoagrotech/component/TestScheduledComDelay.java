package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.ConsumerService;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class TestScheduledComDelay {

    @Mock
    private ConsumerService mockConsumerService = Mockito.mock(ConsumerService.class);

    @Test
    public void deveExecutar() throws IOException, MessagingException {
        ScheduledComDelay scheduledComDelay = new ScheduledComDelay(mockConsumerService);

        doNothing().when(mockConsumerService).ConsumeMessage(anyString());

        scheduledComDelay.executar();

        verify(mockConsumerService, atMostOnce()).ConsumeMessage(any(String.class));
    }

}
