
package com.tads.dac.saga.sagas.autocadastro;

import com.tads.dac.saga.DTO.ClienteEndDTO;
import com.tads.dac.saga.DTO.MensagemDTO;
import com.tads.dac.saga.model.AutocadastroCliente;
import com.tads.dac.saga.repository.AutocadastroClienteRepository;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tads.dac.saga.util.InterfaceSagaOrquestration;

@Component
public class Saga1AutocadastroClienteProducer implements InterfaceSagaOrquestration{

    @Autowired
    private AmqpTemplate template;
    
    @Autowired
    private ModelMapper mapper;
    
    @Autowired
    private AutocadastroClienteRepository rep;

    
    //Primeiro da sequencia
    @Override
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigProducersAutocadastro.queueAutoClienteCommit, dto);
    }

    @Override
    public void rollbackOrdem(MensagemDTO msg) {
        if(msg.getSagaId() != null){
            Optional<AutocadastroCliente> model = rep.findById(msg.getSagaId());
            if(model.isPresent()){
                ClienteEndDTO dto = mapper.map(model.get(), ClienteEndDTO.class);
                msg.setSendObj(dto);
                template.convertAndSend(ConfigProducersAutocadastro.queueAutoClienteRollback, msg);
                //Faz alguma coisa
                rep.deleteById(msg.getSagaId());
                
            }else{
                System.err.println("Id Não Existe - Rollback de Saga1AutocadastroClienteProducer");
            }
        }else{
            System.err.println("Id não pode ser Null - Rollback de Saga1AutocadastroClienteProducer");
        }
    }
    
}