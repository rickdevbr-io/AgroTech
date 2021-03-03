package br.edu.fiap.integrationsdevelopmenttools.gestaoagrotech.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@ConfigurationProperties
public class ConsumerService {
    private final EmailService emailService;
    private static final String MAIL = "emaildestinobr@mail.com";
    private static final String SUBJECT = "AGROTECH DRONE";
    private static final String TOPIC = "gestaoagrotech";

    @Autowired
    public ConsumerService(EmailService emailService) {
        this.emailService = emailService;
    }
    
    @KafkaListener(topics = TOPIC, groupId = "group_id", containerFactory = "agrotechListener")
    public void ConsumeMessage(String mensagem) throws MessagingException, IOException {
        
        if(!mensagem.isEmpty()) {
        	System.out.println("MENSAGEM enviada: " + mensagem);
        	emailService.sendMail(MAIL, SUBJECT, mensagem);
        }
        
    }
    
}
