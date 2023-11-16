package com.ssafy.maytrip.jwt;
import java.util.Base64;
import java.util.Date;


import com.ssafy.maytrip.domain.Member;
import com.ssafy.maytrip.dto.request.MemberRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

public class JWTUtil {

	private static final String SECRET_KEY = "ssafy"; // 시크릿 키, 토큰 생성과 검증 시 필요

	// JWT 토큰 생성 메소드
	public static String generateToken(Member member) {
		long now = System.currentTimeMillis();
		long expirationSecond = 60*60*2;	//2시간

		Claims claims = Jwts.claims();
		claims.put("memberId", member.getId());
		claims.put("username", member.getUsername());
		claims.put("name", member.getName());
//		claims.put("role", member.getRole());

		return Jwts.builder()
				.setClaims(claims)	//정보 담기
				.setExpiration(new Date(now + 1000*expirationSecond)) // 토큰 만료 시간
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY)		  // encoding 방식
				.compact();	//토큰 생성
	}

	// JWT 토큰 유효성 검증 메소드(SecretKey 필요)
	public static Jws<Claims> validateToken(String token) {
		return Jwts.parser()
				.setSigningKey(SECRET_KEY)
				.parseClaimsJws(token);
	}

	// JWT 토큰 decode(SecretKey 필요X)
	public static String decodeToken(String token) {
		String[] splitToken = token.split("\\.");
		String body = splitToken[1];
		
		//디코딩
		Base64.Decoder decoder = Base64.getDecoder();
		String decodedBody = new String(decoder.decode(body));
		
		return decodedBody;
	}


	public static void main(String[] args) {
		// 테스트용 코드
		Member member = Member.builder()
				.username("ssafy")
				.name("ssafy")
				.build();

		//토큰 생성
		String token = generateToken(member);
		System.out.println("생성된 토큰 : " + token);

		//토큰 디코딩 ( 단순 정보만 추출 ) => SecretKey 필요X 누구나 추출 가능 
		String decoded = decodeToken(token);
		System.out.println("디코딩된 토큰 : "+decoded);
		
		//유효성 검증 테스트 => SecretKey 필요 누구나 할 수 없음
		try {
			Jws<Claims> parsedToken = validateToken(token);
			System.out.println("Body: " + parsedToken.getBody());
			System.out.println("유효한 토큰.");
		} catch (Exception e) {
			System.out.println("유효하지 않거나 만료된 토큰.");
		}
	}
}
