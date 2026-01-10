package glsi.api.tp_jee_egab.Service;

import glsi.api.tp_jee_egab.DTO.AuthRequest;
import glsi.api.tp_jee_egab.DTO.LoginRequest;
import glsi.api.tp_jee_egab.DTO.RegisterRequest;
import glsi.api.tp_jee_egab.DTO.UserDTO;
import glsi.api.tp_jee_egab.Model.Admin;
import glsi.api.tp_jee_egab.Model.Agent;
import glsi.api.tp_jee_egab.Model.Client;
import glsi.api.tp_jee_egab.Model.User;
import glsi.api.tp_jee_egab.Repository.UserRepository;
import glsi.api.tp_jee_egab.Security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthServiceImpl {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtils jwtUtils;


    //Login
    public AuthRequest login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        if (request.getRole() != null && !user.getRole().equals(request.getRole())) {
            throw new BadCredentialsException("Invalid role");
        }

        String token = jwtUtils.generateToken(user.getEmail(), user.getRole().name());

        return new AuthRequest(token, UserDTO.fromUser(user));
    }

    // REGISTER
    public User register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        User user;
        switch (request.getRole()) {
            case ADMIN:
                user = new Admin();
                break;
            case AGENT:
                Agent agent = new Agent();
                agent.setMatricule(request.getMatricule());
                user = agent;
                break;
            case CLIENT:
                Client client = new Client();
                client.setFirstName(request.getFirstName());
                client.setLastName(request.getLastName());
                client.setDateNaissance(request.getDateNaissance());
                client.setCity(request.getCity());
                client.setNationality(request.getNationality());
                client.setNumberNationality(request.getNumberNationality());
                user = client;
                break;
            default:
                throw new IllegalArgumentException("Invalid role");
        }

        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

}
