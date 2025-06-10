package org.example.construction.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class RedisVerificationCodeService {
    private final RedisTemplate<String, String> redisTemplate;
    public static final String VALIDATION_CODE_KEY = "validationCode:";

    public void saveToken(String email,String code,long expirationDate) {
        String redisKey = getRedisKey(email);
        redisTemplate.opsForValue().set(redisKey, code,expirationDate, TimeUnit.MINUTES);
    }

    public Boolean codeIsValid(String validationCode,String email) {
       String redisKey = getRedisKey(email);
       String code = redisTemplate.opsForValue().get(redisKey);
       if (code == null) {
           return false;
       }
        System.out.println(code +"   " + validationCode);
       return code.equals(validationCode);
    }

    public String getRedisKey(String email) {
        return VALIDATION_CODE_KEY + email;
    }
}
