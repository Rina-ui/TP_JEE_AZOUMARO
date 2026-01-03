package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "compte")
@NoArgsConstructor
@AllArgsConstructor
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private TypeCompte account;
    private BigDecimal sold;

    @ManyToOne
    private Client client;

}

