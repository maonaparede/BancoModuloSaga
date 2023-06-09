
package com.tads.dac.saga.sagas.alteragerente;

import com.tads.dac.saga.sagas.alteraperfil.*;
import com.tads.dac.saga.DTO.ClienteEndDTO;
import com.tads.dac.saga.DTO.MensagemDTO;
import com.tads.dac.saga.model.PerfilClienteUpdateSaga;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.tads.dac.saga.repository.PerfilClienteUpdateRepository;

@Component
public class Saga2AlteraGerenteAuthProducer{

    @Autowired
    private AmqpTemplate template;
    
    public void commitOrdem(MensagemDTO dto) {
        template.convertAndSend(ConfigProducersAlteraGerente.queueAlteraGerenteAuth, dto);
    }

    
}