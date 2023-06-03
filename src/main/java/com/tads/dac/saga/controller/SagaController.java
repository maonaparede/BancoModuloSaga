
package com.tads.dac.saga.controller;

import com.tads.dac.saga.DTO.ClienteAutocadastroDTO;
import com.tads.dac.saga.DTO.ClienteEndDTO;
import com.tads.dac.saga.DTO.GerenteDTO;
import com.tads.dac.saga.model.AutocadastroCliente;
import com.tads.dac.saga.sagas.alteraperfil.PerfilSagaServiceInit;
import com.tads.dac.saga.sagas.autocadastro.SagaAutocadastroInitService;
import com.tads.dac.saga.sagas.inseregerente.InsertGerenteSagaInitService;
import com.tads.dac.saga.sagas.removegerente.RemoveGerenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class SagaController {
    
    @Autowired
    private PerfilSagaServiceInit updatePerfilserv;
    
    @Autowired
    private RemoveGerenteService gerRemoveServ;
    
    @Autowired
    private InsertGerenteSagaInitService gerInsertServ;
    
    @Autowired
    private SagaAutocadastroInitService autocadastroServ;

    
    @PostMapping("/saga")
    public ResponseEntity<?> sagaUpdateContaLimiteCliente(@RequestBody ClienteEndDTO dto){
        
        updatePerfilserv.initSaga(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saga/ger/{id}")
    public ResponseEntity<?> sagaRemoveGerente(@PathVariable("id") Long id){      
        gerRemoveServ.initSaga(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/saga/ger/insert")
    public ResponseEntity<?> sagaRemoveGerente(@RequestBody GerenteDTO dto){      
        gerInsertServ.initSagaInsertGerente(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @PostMapping("/saga/cli/auto")
    public ResponseEntity<?> sagaAutocadastro(@RequestBody ClienteAutocadastroDTO dto){      
        autocadastroServ.initSagaAutocadastro(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
