package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.service.JwtTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class JwtTokenServiceImpl implements JwtTokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    @Value("${jwt.token.expiration}")
    private int jwtExpiration;

    @Override
    public String generateToken(Authentication authentication) {
        Instant now = Instant.now();

        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        System.out.println(scope);

        JwtClaimsSet claims = JwtClaimsSet
                .builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(jwtExpiration, ChronoUnit.SECONDS))
                .subject(authentication.getName())
                .claim("scope", scope)
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS512).build();
        JwtEncoderParameters parameters = JwtEncoderParameters.from(header, claims);
        return this.jwtEncoder.encode(parameters).getTokenValue();
    }

    @Override
    public String getUsername(String token) {
        Jwt jwt = jwtDecoder.decode(token);
        return jwt.getSubject();
    }
}
