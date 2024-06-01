package lk.ijse.gdse.hello_shoes.service.impl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lk.ijse.gdse.hello_shoes.service.JWTService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@Slf4j
public class JWTServiceIMPL implements JWTService {
    @Value("${token.signing.key}")
    private String jwtKey;
    @Override
    public String extractUserName(String token) {
        return extractClaim(token,Claims::getSubject);
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(),userDetails);
    }

    @Override
    public boolean validToken(String token, UserDetails userDetails) {
        var username = extractUserName(token);
        log.info("Username {}",username);
        log.info("Username Ok {}",username.equals(userDetails.getUsername()));
        log.info("Is token Expired {}",isTokenExpired(token));
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    public <T> T extractClaim(String token, Function<Claims,T> claimResolve){
        final Claims allClaims = getAllClaims(token);
        return claimResolve.apply(allClaims);
    }

    private String generateToken(Map<String,Object> extractClaim, UserDetails userDetails){
        extractClaim.put("role",userDetails.getAuthorities());
        Date now = new Date();
        Date expire = new Date(now.getTime() + 1000*60*60*24);
        Date refreshToken = new Date(now.getTime() + 1000*60*60*24);

        String accessToken = Jwts.builder().setClaims(extractClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(now)
                .setExpiration(expire)
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
        return accessToken;


    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims getAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey(){
        byte[] decode = Decoders.BASE64.decode(jwtKey);
        return Keys.hmacShaKeyFor(decode);
    }
}
