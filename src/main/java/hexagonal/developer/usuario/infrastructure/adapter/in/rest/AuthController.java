package hexagonal.developer.usuario.infrastructure.adapter.in.rest;

import hexagonal.developer.shared.infrastructure.security.jwt.JwtService;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UsuarioLoginRequest request){
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuarioUsuario(),
                        request.getContrasenaHash()
                )
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generarToken(userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority());
        return ResponseEntity.ok(Map.of("token", token));
    }
}