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

    public void saveToken(String code,long expirationDate) {
        redisTemplate.opsForValue().set(VALIDATION_CODE_KEY, code,expirationDate, TimeUnit.MINUTES);
    }

    public Boolean codeIsValid(String validationCode) {
       String code = redisTemplate.opsForValue().get(VALIDATION_CODE_KEY);
       if (code == null) {
           return false;
       }
       return code.equals(validationCode);
    }
}
