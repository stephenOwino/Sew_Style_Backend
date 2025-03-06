package com.stephenowinosewstyle.Sew_Style_Backend.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class JWTService {

        @Value("${jwt.secret}")
        private String secretKey;

        @Value("${jwt.expiration}")
        private long expirationTime;

        private static final Logger logger = Logger.getLogger(JWTService.class.getName());

        // Generate token for UserDetails
        public String generateToken(UserDetails userDetails) {
                Map<String, Object> claims = new HashMap<>();
                List<String> roles = userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .map(role -> role.replace("ROLE_", "")) // Strip ROLE_ prefix for storage
                        .collect(Collectors.toList());
                claims.put("roles", roles);

                return Jwts.builder()
                        .setClaims(claims)
                        .setSubject(userDetails.getUsername())
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                        .signWith(getKey(), SignatureAlgorithm.HS256)
                        .compact();
        }

        // Extract username (email in your case) from token
        public String extractUsername(String token) {
                try {
                        return extractClaim(token, Claims::getSubject);
                } catch (Exception e) {
                        logger.warning("Error extracting username from token: " + e.getMessage());
                        return null;
                }
        }

        // Extract roles from token
        public List<String> extractRoles(String token) {
                try {
                        return extractClaim(token, claims -> claims.get("roles", List.class));
                } catch (Exception e) {
                        logger.warning("Error extracting roles from token: " + e.getMessage());
                        return Collections.emptyList();
                }
        }

        // Extract specific claim
        private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
                final Claims claims = extractAllClaims(token);
                return claimResolver.apply(claims);
        }

        // Parse all claims from the token
        private Claims extractAllClaims(String token) {
                return Jwts.parserBuilder()
                        .setSigningKey(getKey())
                        .build()
                        .parseClaimsJws(token)
                        .getBody();
        }

        // Validate the token
        public boolean isValid(String token, UserDetails userDetails) {
                try {
                        final String username = extractUsername(token);
                        return (username != null && username.equals(userDetails.getUsername()) && !isTokenExpired(token));
                } catch (Exception e) {
                        logger.warning("Error validating token: " + e.getMessage());
                        return false;
                }
        }

        // Check if the token has expired
        private boolean isTokenExpired(String token) {
                try {
                        return extractExpiration(token).before(new Date());
                } catch (Exception e) {
                        logger.warning("Error checking token expiration: " + e.getMessage());
                        return true;
                }
        }

        // Extract expiration date from token
        private Date extractExpiration(String token) {
                return extractClaim(token, Claims::getExpiration);
        }

        // Helper method to get the signing key
        private SecretKey getKey() {
                byte[] keyBytes = Decoders.BASE64.decode(secretKey);
                return Keys.hmacShaKeyFor(keyBytes);
        }
}
