
package com.tads.dac.saga.controller;

import com.tads.dac.saga.DTO.ClienteEndDTO;
import com.tads.dac.saga.sagas.alteraperfil.PerfilSagaService;
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
    private PerfilSagaService serv;
    
    @Autowired
    private RemoveGerenteService gerServ;

    
    @PostMapping("/saga")
    public ResponseEntity<?> sagaUpdateContaLimiteCliente(@RequestBody ClienteEndDTO dto){
        
        serv.initSaga(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/saga/ger/{id}")
    public ResponseEntity<?> sagaRemoveGerente(@PathVariable("id") Long id){      
        gerServ.initSaga(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}
