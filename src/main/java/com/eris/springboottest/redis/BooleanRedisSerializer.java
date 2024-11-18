package com.eris.springboottest.redis;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

public class BooleanRedisSerializer implements RedisSerializer<Boolean> {
    private final Charset charset;

    public BooleanRedisSerializer() {
        this.charset = StandardCharsets.UTF_8;
    }

    public Boolean deserialize(@Nullable byte[] bytes) {
        if (Objects.isNull(bytes)) {
            return null;
        } else {
            String value = new String(bytes, this.charset);
            return Boolean.valueOf(value);
        }
    }

    public byte[] serialize(@Nullable Boolean bool) {
        bool = Objects.nonNull(bool) ? bool : Boolean.FALSE;
        return bool.toString().getBytes();
    }
}
