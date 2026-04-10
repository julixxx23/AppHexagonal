package hexagonal.developer.usuario.infrastructure.adapter.in.rest;

import hexagonal.developer.shared.infrastructure.security.jwt.JwtService;
import hexagonal.developer.usuario.domain.model.Usuario;
import hexagonal.developer.usuario.domain.port.in.GuardarUsuarioPort;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioCreateRequest;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioLoginRequest;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.dto.UsuarioResponse;
import hexagonal.developer.usuario.infrastructure.adapter.in.rest.mapper.UsuarioRestMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final GuardarUsuarioPort guardarUsuarioPort;
    private final UsuarioRestMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody UsuarioLoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsuarioUsuario(),
                        request.getContrasenaHash()
                )
        );
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String token = jwtService.generarToken(
                userDetails.getUsername(),
                userDetails.getAuthorities().iterator().next().getAuthority()
        );
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<UsuarioResponse> register(@Valid @RequestBody UsuarioCreateRequest request) {
        Usuario usuario = guardarUsuarioPort.guardar(mapper.toDomain(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponse(usuario));
    }
}