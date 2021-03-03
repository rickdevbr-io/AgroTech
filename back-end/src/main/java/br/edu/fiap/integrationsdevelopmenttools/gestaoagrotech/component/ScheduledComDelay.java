package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.component;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service.ConsumerService;

@Component
public class ScheduledComDelay {

    private final ConsumerService consumerService;

    @Autowired
    public ScheduledComDelay(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @Scheduled(fixedDelay = 10000)
    public void executar() throws MessagingException, IOException {

    	String mensagem = new String();
        consumerService.ConsumeMessage(mensagem);

        System.out.println("Executou o Scheduled com delay");
    }
}