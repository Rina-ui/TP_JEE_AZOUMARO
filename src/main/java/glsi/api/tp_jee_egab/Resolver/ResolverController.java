package glsi.api.tp_jee_egab.Resolver;

import glsi.api.tp_jee_egab.DTO.AuthRequest;
import glsi.api.tp_jee_egab.DTO.LoginRequest;
import glsi.api.tp_jee_egab.DTO.RegisterRequest;
import glsi.api.tp_jee_egab.Service.AuthService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
class ResolverController {

    private AuthService authService;

    @MutationMapping
    public AuthRequest register(@Argument RegisterRequest request) {
        return authService.register(request);
    }

    @MutationMapping
    public AuthRequest login(@Argument LoginRequest request) {
        return authService.login(request);
    }


}
