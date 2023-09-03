package com.example.jwt_spring.base.jwt;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	private SecretKey cachedSecretKey;

	@Value("${custom.jwt.secretKey}")
	private String secretKeyPlain;

	private SecretKey _getSecretKey() {
		String keyBase64Encoded = Base64.getEncoder().encodeToString(secretKeyPlain.getBytes());
		return Keys.hmacShaKeyFor(keyBase64Encoded.getBytes());
	}

	public SecretKey getSecretKey() {
		if (cachedSecretKey == null) cachedSecretKey = _getSecretKey();

		return cachedSecretKey;
	}
}