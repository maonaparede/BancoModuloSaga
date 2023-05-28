
package com.tads.dac.saga.sagas.alteraperfil;

import com.tads.dac.saga.DTO.ContaDTO;
import com.tads.dac.saga.DTO.MensagemDTO;
import com.tads.dac.saga.model.PerfilContaUpdateSaga;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tads.dac.saga.util.InterfaceSagaOrquestration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.handler.annotation.Payload;
import com.tads.dac.saga.repository.PerfilContaUpdateRepository;

@Component
public class Saga2PerfilContaProducer implements InterfaceSagaOrquestration{

    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private PerfilContaUpdateRepository rep;


    //ClienteEndDTO
    @Override
    public void commitOrdem(MensagemDTO dto) {        
        template.convertAndSend(ConfigProducersPerfil.queuePerfilConta, dto);       
    }

    @Override
    public void rollbackOrdem(MensagemDTO msg) {
        if(msg.getSagaId() != null){
            Optional<PerfilContaUpdateSaga> conta = rep.findById(msg.getSagaId());
            if(conta.isPresent()){
                ContaDTO dto = mapper.map(conta.get(), ContaDTO.class);
                msg.setSendObj(dto);
                template.convertAndSend(ConfigProducersPerfil.queuePerfilContaRollback, msg);
                
                //Faz alguma coisa
                rep.deleteById(msg.getSagaId());
            }else{
                System.err.println("Id Não Existe - Rollback de PerfilContaProducer");
            }
        }else{
            System.err.println("Id não pode ser Null - Rollback de PerfilContaProducer");
        }
    }
    
}
