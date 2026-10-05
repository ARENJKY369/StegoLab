package com.steglab;
import java.nio.charset.StandardCharsets;import java.security.SecureRandom;import java.util.*;import javax.crypto.*;import javax.crypto.spec.*;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/crypto") public class CryptoController{
 static byte[] key(String p,byte[] salt)throws Exception{var s=SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");return s.generateSecret(new PBEKeySpec(p.toCharArray(),salt,100000,256)).getEncoded();}
 @PostMapping("/encrypt") public Map<String,Object> encrypt(@RequestBody Secret p)throws Exception{if(p.password().length()<8)throw new IllegalArgumentException("Use a password of at least 8 characters.");var salt=new byte[16];var nonce=new byte[12];new SecureRandom().nextBytes(salt);new SecureRandom().nextBytes(nonce);var c=Cipher.getInstance("AES/GCM/NoPadding");c.init(Cipher.ENCRYPT_MODE,new SecretKeySpec(key(p.password(),salt),"AES"),new GCMParameterSpec(128,nonce));var cipherText=c.doFinal(p.text().getBytes(StandardCharsets.UTF_8));var out=new byte[28+cipherText.length];System.arraycopy(salt,0,out,0,16);System.arraycopy(nonce,0,out,16,12);System.arraycopy(cipherText,0,out,28,cipherText.length);return Map.of("payload",Base64.getUrlEncoder().withoutPadding().encodeToString(out),"algorithm","AES-256-GCM","iterations",100000);}
 public record Secret(String text,String password){}
}
