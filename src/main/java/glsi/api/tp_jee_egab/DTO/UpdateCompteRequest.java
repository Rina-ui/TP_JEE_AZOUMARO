package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompteRequest {
    private String accountNumber;
    private BigDecimal sold;
    private TypeCompte account;
}
