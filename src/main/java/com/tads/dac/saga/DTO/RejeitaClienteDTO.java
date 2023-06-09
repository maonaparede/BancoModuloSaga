
package com.tads.dac.saga.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RejeitaClienteDTO {
    private Long idCliente;
    private Long idConta;
    private String email;
}
