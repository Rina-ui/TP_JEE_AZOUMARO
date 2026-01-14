package glsi.api.tp_jee_egab.DTO;

import glsi.api.tp_jee_egab.Model.Compte;
import glsi.api.tp_jee_egab.Model.TypeCompte;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompteDTO {
    private Long id;
    private String accountNumber;
    private BigDecimal sold;
    private TypeCompte typeCompte;
    private Boolean actif = true;

  public static CompteDTO fromCompte (Compte compte) {
      CompteDTO dto = new CompteDTO();
      dto.setId(compte.getId());
      dto.setAccountNumber(compte.getAccountNumber());
      dto.setSold(compte.getSold());
      dto.setTypeCompte(compte.getTypeCompte());
      dto.setActif(compte.getActif());

      return dto;
  }
}
