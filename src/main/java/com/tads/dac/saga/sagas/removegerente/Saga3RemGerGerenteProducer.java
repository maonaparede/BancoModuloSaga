
package com.tads.dac.saga.sagas.removegerente;

import com.tads.dac.saga.DTO.MensagemDTO;
import com.tads.dac.saga.model.RemoveGerenteGerente;
import com.tads.dac.saga.repository.RemoveGerenteGerenteRepository;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;

@Component
public class Saga3RemGerGerenteProducer{

    @Autowired
    private AmqpTemplate template;  
    
    @Autowired
    private Saga2RemGerContaProducer prev;
    
    
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigProducersRemGerente.queueGerenteRemGerenteCommit, dto);
    }

    public void rollback(@Payload MensagemDTO msg) {
        //Se deu Erro Rollback na fase anterior
        if(msg.getMensagem() != null){
            
          prev.rollbackOrdem(msg);  
        }
        
        
        //Faz algo
    }    
    
}
