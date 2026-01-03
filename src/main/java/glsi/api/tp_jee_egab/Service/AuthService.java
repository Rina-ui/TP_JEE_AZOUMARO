package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.AuthRequest;
import glsi.api.tp_jee_egab.DTO.LoginRequest;
import glsi.api.tp_jee_egab.DTO.RegisterRequest;

public interface AuthService {

    AuthRequest register(RegisterRequest request);
    AuthRequest login(LoginRequest request);

}
