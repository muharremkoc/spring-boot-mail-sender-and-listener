package com.example.springbootmailartemis.services.listener;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DispatcherService {

    final JmsTemplate jmsTemplate;


    @Value("${jms.queue}")
    String jmsQueue;

    public void sendMessage(String message){
        jmsTemplate.convertAndSend(jmsQueue,message);
    }
}
