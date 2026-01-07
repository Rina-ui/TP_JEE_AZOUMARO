package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.AuthRequest;
import glsi.api.tp_jee_egab.DTO.LoginRequest;
import glsi.api.tp_jee_egab.DTO.RegisterRequest;
import glsi.api.tp_jee_egab.Model.User;
import glsi.api.tp_jee_egab.Repository.UserRepository;
import glsi.api.tp_jee_egab.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;

    public AuthRequest register(RegisterRequest request){

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        String token = jwtUtils.generateToken(user.getEmail());

        return new AuthRequest(token, user.getPassword());

    }

    public AuthRequest login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Password not match");
        }

        String token = jwtUtils.generateToken(user.getEmail());

        return new AuthRequest(token, user.getPassword());
    }

}
