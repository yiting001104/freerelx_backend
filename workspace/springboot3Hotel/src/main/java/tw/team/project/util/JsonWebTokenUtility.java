package tw.team.project.util;

import java.util.Base64;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.Password;
import jakarta.annotation.PostConstruct;

@Component
public class JsonWebTokenUtility {
	@Value("${jwt.token.expire}")
	private long expire;

	private byte[] base64EncodedSecret;	//用在簽章
	private char[] charArraySecret;		//用在加密
	@PostConstruct
	public void init() {
		//TODO：應該實作從資料庫抓出密鑰
		String secret = "ABCDEFGHJKLM23456789npqrstuvwxyz";

		//將密鑰使用base64編碼
		base64EncodedSecret = Base64.getEncoder().encode(secret.getBytes());
		charArraySecret = new String(base64EncodedSecret).toCharArray();
	}

	public String createEncryptedToken(String data, Long lifespan) {
		java.util.Date now = new java.util.Date();
		if(lifespan==null) {
			lifespan = this.expire * 60 * 1000;
		}
		long end = System.currentTimeMillis() + lifespan;
		java.util.Date expiredate = new java.util.Date(end);

		//建立密碼
		Password password = Keys.password(charArraySecret);
		JwtBuilder builder = Jwts.builder()
				.subject(data)					//JWT主體內容
				.issuedAt(now)					//建立時間
				.expiration(expiredate)			//過期時限
				.encryptWith(password,			//加密：避免內容被讀取
					Jwts.KEY.PBES2_HS512_A256KW,
					Jwts.ENC.A256GCM);

		String token = builder.compact();
		return token;
	}
	public String validateEncryptedToken(String token) {
		//建立密碼
		Password password = Keys.password(charArraySecret);
		JwtParser parser = Jwts.parser()
				.decryptWith(password)			//解密：以便讀取內容
				.build();
		try {
			Claims claims = parser.parseEncryptedClaims(token).getPayload();

			//取出JWT主體內容
			String subject = claims.getSubject();
			return subject;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String createToken(String data, Long lifespan) {
		java.util.Date now = new java.util.Date();
		if(lifespan==null) {
			lifespan = expire * 60 * 1000;
		}
		long end = System.currentTimeMillis() + lifespan;
		java.util.Date expiredate = new java.util.Date(end);

		//使用HMACS-SHA演算法建立簽章密鑰
		SecretKey secretKey = Keys.hmacShaKeyFor(base64EncodedSecret);
		JwtBuilder builder = Jwts.builder()
				.subject(data)					//JWT主體內容
				.issuedAt(now)					//建立時間
				.expiration(expiredate)			//過期時限
				.signWith(secretKey);			//使用密鑰簽章：避免內容被竄改
		String token = builder.compact();
		return token;
	}

	public String validateToken(String token) {
		//使用HMACS-SHA演算法建立簽章密鑰
		SecretKey secretKey = Keys.hmacShaKeyFor(base64EncodedSecret);
		JwtParser parser = Jwts.parser()
				.verifyWith(secretKey)		//使用密鑰驗證簽章：避免內容被竄改
				.build();
		try {
			Claims claims = parser.parseSignedClaims(token).getPayload();

			//取出JWT主體內容
			String subject = claims.getSubject();
			return subject;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
