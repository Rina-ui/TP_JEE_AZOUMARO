package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "compte")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private TypeCompte typeCompte;
    private BigDecimal sold;

    private Boolean actif = true;

    @ManyToOne
    private Client client;

}

