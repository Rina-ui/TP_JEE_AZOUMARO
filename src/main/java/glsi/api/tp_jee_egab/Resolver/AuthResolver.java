package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.AuthRequest;
import glsi.api.tp_jee_egab.DTO.LoginRequest;
import glsi.api.tp_jee_egab.DTO.RegisterRequest;
import glsi.api.tp_jee_egab.Model.Role;
import glsi.api.tp_jee_egab.Model.User;
import glsi.api.tp_jee_egab.Service.AuthService;
import glsi.api.tp_jee_egab.Service.AuthServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
public class AuthResolver {

    private final AuthServiceImpl authService;

    @MutationMapping
    public AuthRequest login(@Argument LoginInput input) {
        LoginRequest request = new LoginRequest();
        request.setEmail(input.email());
        request.setPassword(input.password());
        request.setRole(input.role());

        return authService.login(request);
    }

    @MutationMapping
    public User register(@Argument RegisterInput input) {
        RegisterRequest request = new RegisterRequest();
        request.setEmail(input.email());
        request.setPassword(input.password());
        request.setRole(input.role());
        request.setFirstName(input.firstName());
        request.setLastName(input.lastName());
        request.setMatricule(input.matricule());

        if (input.dateNaissance() != null) {
            request.setDateNaissance(LocalDate.parse(input.dateNaissance()));
        }
        request.setCity(input.city());
        request.setNationality(input.nationality());
        request.setNumberNationality(input.numberNationality());

        return authService.register(request);
    }



    @QueryMapping
    public String hello() {
        return "Hello from GraphQL!";
    }

    // Record pour les inputs du graphQL
    record LoginInput(String email, String password, Role role) {}

    record RegisterInput(
            String email,
            String password,
            Role role,
            String firstName,
            String lastName,
            String matricule,
            String department,
            String dateNaissance,
            String city,
            String nationality,
            Integer numberNationality
    ) {}
}