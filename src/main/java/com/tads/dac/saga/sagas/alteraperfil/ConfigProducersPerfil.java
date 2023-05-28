
package com.tads.dac.saga.sagas.alteraperfil;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ConfigProducersPerfil {
    
    public static String queuePerfilCliente = "perfil-cliente-saga";
    
    public static String queuePerfilClienteRollback = "perfil-cliente-saga-rollback";
    
    public static String queuePerfilClienteReceive = "perfil-cliente-saga-receive";
    
    public static String queuePerfilConta = "perfil-conta-saga";
    
    public static String queuePerfilContaRollback = "perfil-conta-saga-rollback";
    
    public static String queuePerfilContaReceive = "perfil-conta-saga-receive";
    
    @Bean
    public Queue queuePerfilCliente(){
        return new Queue(queuePerfilCliente);
    }
    
    @Bean
    public Queue queuePerfilClienteRollback(){
        return new Queue(queuePerfilClienteRollback);
    }

    @Bean
    public Queue queuePerfilClienteReceive(){
        return new Queue(queuePerfilClienteReceive);
    }
    
    @Bean
    public Queue queuePerfilConta(){
        return new Queue(queuePerfilConta);
    }
    
    @Bean
    public Queue queuePerfilContaRollback(){
        return new Queue(queuePerfilContaRollback);
    } 
    
    @Bean
    public Queue queuePerfilContaReceive(){
        return new Queue(queuePerfilContaReceive);
    } 
}
