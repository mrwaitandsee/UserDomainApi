package mrwaitandsee.userdomainapi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import mrwaitandsee.userdomainapi.dto.UserRegistrationResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;

    public String getToken(Map<String, ?> map) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        long expireTime = (new Date().getTime()) + 60000 * 60;
        Date expireDate = new Date(expireTime);
        return JWT.create()
                .withPayload(map)
                .withExpiresAt(expireDate)
                .sign(algorithm);
    }

    public String getUserId(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        JWTVerifier verifier = JWT.require(algorithm).build();
        DecodedJWT jwt = verifier.verify(token);
        return jwt.getClaims().get("id").asString();
    }
}
