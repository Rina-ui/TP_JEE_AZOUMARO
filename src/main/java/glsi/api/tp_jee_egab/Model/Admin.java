package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "admins")
@AllArgsConstructor
@DiscriminatorValue("ADMIN")
@Data
public class Admin extends User {

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public Admin() {
        super();
        this.setRole(Role.ADMIN);
    }
}