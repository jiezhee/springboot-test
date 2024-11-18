package com.eris.springboottest.redis;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.lang.Nullable;

public class PrefixRedisSerializer implements RedisSerializer<String> {
    private final Charset charset;
    private final String prefix;
    private static final String SPLIT = ":";

    public PrefixRedisSerializer(String prefix) {
        this.charset = StandardCharsets.UTF_8;
        this.prefix = prefix + ":";
    }

    public String deserialize(@Nullable byte[] bytes) {
        if (Objects.isNull(bytes)) {
            return null;
        } else {
            String value = new String(bytes, this.charset);
            return value.startsWith(this.prefix) ? value.replaceFirst(this.prefix, "") : value;
        }
    }

    public byte[] serialize(@Nullable String string) {
        return Objects.isNull(string) ? null : (this.prefix + string).getBytes();
    }
}

