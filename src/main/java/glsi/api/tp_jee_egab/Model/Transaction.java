package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private TypeTransaction transac;

    private BigDecimal amount;
    private LocalDateTime date;

    @ManyToOne
    private Compte compteSource;

    @ManyToOne
    private Compte compteDest;

}
