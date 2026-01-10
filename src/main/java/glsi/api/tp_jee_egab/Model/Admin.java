package glsi.api.tp_jee_egab.Model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "admin")
@DiscriminatorValue("admin_type")
@NoArgsConstructor
@AllArgsConstructor

public class Admin extends User {

    @NotBlank
    private String username;

}
