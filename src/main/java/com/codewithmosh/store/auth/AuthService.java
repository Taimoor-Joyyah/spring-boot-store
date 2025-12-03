package com.codewithmosh.store.auth;

import com.codewithmosh.store.users.UserDto;
import com.codewithmosh.store.users.User;
import com.codewithmosh.store.users.UserMapper;
import com.codewithmosh.store.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ActiveTokenRepository activeTokenRepository;
    private final ActiveTokenCache activeTokenCache;

    public record Tokens(Jwt accessToken, Jwt refreshToken) {
    }

    public Tokens login(String email, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        User user = userRepository.findByEmail(email).orElseThrow();
        Jwt accessToken = jwtService.generateAccessToken(user);
        Jwt refreshToken = jwtService.generateRefreshToken(user);

        String refreshTokenStr = refreshToken.toString();

        activeTokenCache.put(user.getId(), accessToken);

        activeTokenRepository.findById(user.getId()).ifPresentOrElse(
                activeToken -> {
                    activeToken.setToken(refreshTokenStr);
                    activeTokenRepository.save(activeToken);
                },
                () -> activeTokenRepository.save(new ActiveToken(user.getId(), refreshTokenStr))
        );

        return new Tokens(accessToken, refreshToken);
    }

    public void logout() {
        Long userId = currentUserId();
        if (userId == null) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        activeTokenCache.remove(userId);
        activeTokenRepository.deleteById(userId);
    }

    public Long currentUserId() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof UsernamePasswordAuthenticationToken) {
            return (Long) authentication.getPrincipal();
        }

        return null;
    }

    public JwtResponse refresh(String refreshToken) {
        Jwt jwt = jwtService.parse(refreshToken);
        if (jwt == null || jwt.isExpired()) {
            throw new BadCredentialsException("Invalid Refresh Token");
        }

        User user = userRepository.findById(jwt.getUserId()).orElseThrow();
        ActiveToken activeToken = activeTokenRepository.findById(user.getId()).orElse(null);
        if (activeToken == null) {
            throw new BadCredentialsException("User not logged in");
        } else if (!refreshToken.equals(activeToken.getToken())) {
            throw new BadCredentialsException("Old Refresh Token");
        }

        Jwt accessToken = jwtService.generateAccessToken(user);
        activeTokenCache.put(user.getId(), accessToken);

        return new JwtResponse(accessToken.toString());
    }

    public User getCurrentUser() {
        Long userId = currentUserId();
        if (userId == null) {
            throw new AuthenticationCredentialsNotFoundException("User not logged in");
        }

        return userRepository.findById(userId).orElseThrow();
    }

    public UserDto getCurrentUserMapped() {
        return userMapper.toDto(getCurrentUser());
    }
}
