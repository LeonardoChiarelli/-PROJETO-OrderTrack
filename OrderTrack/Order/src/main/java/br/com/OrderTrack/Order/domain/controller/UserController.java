package br.com.OrderTrack.Order.domain.controller;

import br.com.OrderTrack.Order.domain.dto.SignInDTO;
import br.com.OrderTrack.Order.domain.dto.SignUpDTO;
import br.com.OrderTrack.Order.domain.model.User;
import br.com.OrderTrack.Order.domain.service.UserService;
import br.com.OrderTrack.Order.general.security.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderTrack")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/signUp")
    @Transactional
    public ResponseEntity<String> signUp(@RequestBody @Valid SignUpDTO dto) {
        service.signUp(dto);

        return ResponseEntity.ok("User signed up successfully");
    }

    @PostMapping("/signIn")
    public ResponseEntity<String> signIn(@RequestBody @Valid SignInDTO dto){
        try{
            var authenticationManager = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
            var authentication = manager.authenticate(authenticationManager);

            var tokenJWT = tokenService.gerarToken((User) authentication.getPrincipal());

            return ResponseEntity.ok(tokenJWT);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + e.getMessage());
        }
    }
}
