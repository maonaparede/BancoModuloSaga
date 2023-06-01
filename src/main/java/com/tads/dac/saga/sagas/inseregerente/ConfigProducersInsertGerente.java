
package com.tads.dac.saga.sagas.inseregerente;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigProducersInsertGerente {
    
    //1° Passo
    public static String queueGerenteInsertGerente = "ger-save-gerente-saga";
    
    public static String queueGerenteInsertGerenteReceive = "ger-save-gerente-saga-receive";
    
    public static String queueGerenteInsertGerenteRollback = "ger-save-gerente-saga-rollback";
    
    //2° Passo
    public static String queueGerenteInsertContaCommit = "ger-save-conta-saga";
  
    public static String queueGerenteInsertContaReceive = "ger-save-conta-saga-receive";
    
    public static String queueGerenteInsertContaRollback = "ger-save-conta-saga-rollback";
    
    
    //1° Passo
    @Bean
    public Queue queueGerenteInsertGerente() {
        return new Queue(queueGerenteInsertGerente);
    }

    @Bean
    public Queue queueGerenteInsertGerenteReceive() {
        return new Queue(queueGerenteInsertGerenteReceive);
    }
 
    @Bean
    public Queue queueGerenteInsertGerenteRollback() {
        return new Queue(queueGerenteInsertGerenteRollback);
    }
    
    //2° Passo
    @Bean
    public Queue queueGerenteInsertContaCommit(){
        return new Queue(queueGerenteInsertContaCommit);
    }
    
    @Bean
    public Queue queueGerenteInsertContaReceive(){
        return new Queue(queueGerenteInsertContaReceive);
    }
    
    @Bean
    public Queue queueGerenteInsertContaRollback(){
        return new Queue(queueGerenteInsertContaRollback);
    }
    
}
