package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.UserDto;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
class AuthController {
    private final JwtConfig jwtConfig;
    private final AuthService authService;

    @PostMapping("/login")
    public JwtResponse login(
            @Valid @RequestBody LoginRequest loginRequest,
            HttpServletResponse response) {
        var jsonResponse = authService.login(loginRequest.getEmail(), loginRequest.getPassword());

        var cookie = new Cookie("token", jsonResponse.refreshToken().toString());
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(jwtConfig.getRefreshTokenExpiration());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return new JwtResponse(jsonResponse.accessToken().toString());
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletResponse response) {
        authService.logout();

        var cookie = new Cookie("token", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/auth/refresh");
        cookie.setMaxAge(0);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(
            @CookieValue("token") String refreshToken
    ) {
        return authService.refresh(refreshToken);
    }

    @GetMapping("/current")
    public UserDto getCurrentUser() {
        return authService.getCurrentUserMapped();
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Void> badCredentialsException() {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
