package com.eris.springboottest.redis;

import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions.RefreshTrigger;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

//@Configuration
@ConditionalOnClass({RedisOperations.class})
@EnableConfigurationProperties({RedisProperties.class})
public class RedisUtils {
    private static final Logger log = LoggerFactory.getLogger(RedisUtils.class);
    private static final String COMPARE_AND_DELETE_SCRIPT = "if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return 'true' else return 'false' end";
    private static final String DEFAULT_PREFIX = "default";
    private static final String PREFIX_KEY = "spring.application.name";
    private static final Integer DEFAULT_REDIRECTS = 3;
    private static final BooleanRedisSerializer BOOLEAN_REDIS_SERIALIZER = new BooleanRedisSerializer();

    /**
     * 线程安全的
     */
    private static volatile StringRedisTemplate redisTemplate;
    @Autowired
    private RedisProperties redisProperties;
    @Value("${spring.application.name:default}")
    private String prefix;
    @Value("${redis.prefix.switch:true}")
    private boolean useRedisPrefixSwitch;

    public RedisUtils() {
    }

    public static boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public static String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public static void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public static void set(String key, String value, long expireInMill) {
        redisTemplate.opsForValue().set(key, value, expireInMill, TimeUnit.MILLISECONDS);
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    public static void expire(String key, long expireInMill) {
        redisTemplate.expire(key, expireInMill, TimeUnit.MILLISECONDS);
    }

    public static String rightPopAndLeftPush(String popKey, String pushKey) {
        return (String) redisTemplate.opsForList().rightPopAndLeftPush(popKey, pushKey);
    }

    public static String rPop(String key) {
        return (String) redisTemplate.opsForList().rightPop(key);
    }

    public static String lPop(String key) {
        return (String) redisTemplate.opsForList().leftPop(key);
    }

    public static void rPush(String key, String value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    public static void lPush(String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
    }

    public static void rPushAll(String key, List<String> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    public static void lPushAll(String key, List<String> values) {
        redisTemplate.opsForList().leftPushAll(key, values);
    }

    public static Long setAdd(String key, String... value) {
        return redisTemplate.opsForSet().add(key, value);
    }

    public static Long setRemove(String key, String... value) {
        return redisTemplate.opsForSet().remove(key, value);
    }

    public static Boolean setContains(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public static Set<String> setGetAll(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public static Boolean setNxEx(String key, String ip, long expireInMill) {
        return redisTemplate.opsForValue().setIfAbsent(key, ip, expireInMill, TimeUnit.MILLISECONDS);
    }

    public static Boolean setIfAbsent(String key, String value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    public static Boolean unlock(String key, String ip) {
        RedisScript<Boolean> script = new DefaultRedisScript("if redis.call('get',KEYS[1]) == ARGV[1] then redis.call('del',KEYS[1]) return 'true' else return 'false' end", Boolean.class);
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        return (Boolean) redisTemplate.execute(script, valueSerializer, BOOLEAN_REDIS_SERIALIZER, Collections.singletonList(key), new Object[]{ip});
    }

    public static Long lLen(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public static Long incr(String key) {
        return redisTemplate.opsForValue().increment(key);
    }

    public static String lIndex(String key, Long index) {
        return (String) redisTemplate.opsForList().index(key, index);
    }

    public static Long getExpire(String key) {
        return redisTemplate.getExpire(key);
    }

    public static Object hGet(String key, String hashKey) {
        Object o = redisTemplate.opsForHash().get(key, hashKey);
        return o;
    }

    public static void hSet(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public static void hPutAll(String key, Map<String, String> kvMap) {
        redisTemplate.opsForHash().putAll(key, kvMap);
    }

    public static Long hLen(String key) {
        return redisTemplate.opsForHash().size(key);
    }

    public static Boolean zadd(String key, String member, double score) {
        return redisTemplate.opsForZSet().add(key, member, score);
    }

    public static Set<String> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public static Boolean execScript(String script, List<String> keys, Object... args) {
        RedisScript<Boolean> redisScript = new DefaultRedisScript(script, Boolean.class);
        RedisSerializer<?> valueSerializer = redisTemplate.getValueSerializer();
        return (Boolean) redisTemplate.execute(redisScript, valueSerializer, BOOLEAN_REDIS_SERIALIZER, keys, args);
    }

    @Bean
    @Primary
    public LettuceConnectionFactory lettuceConnectionFactory() {
        if (null != this.redisProperties.getCluster() && null != this.redisProperties.getCluster().getNodes()) {
            log.info("redis集群初始化lettuce");
            RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(this.redisProperties.getCluster().getNodes());
            redisClusterConfiguration.setPassword(this.redisProperties.getPassword());
            if (this.redisProperties.getCluster().getMaxRedirects() == null) {
                this.redisProperties.getCluster().setMaxRedirects(DEFAULT_REDIRECTS);
            }

            redisClusterConfiguration.setMaxRedirects(this.redisProperties.getCluster().getMaxRedirects());
            GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
            if (this.redisProperties.getLettuce() != null && this.redisProperties.getLettuce().getPool() != null) {
                genericObjectPoolConfig.setMaxTotal(this.redisProperties.getLettuce().getPool().getMaxActive());
                genericObjectPoolConfig.setMaxIdle(this.redisProperties.getLettuce().getPool().getMaxIdle());
                genericObjectPoolConfig.setMinIdle(this.redisProperties.getLettuce().getPool().getMinIdle());
                genericObjectPoolConfig.setMaxWaitMillis(this.redisProperties.getLettuce().getPool().getMaxWait().getSeconds());
            }

            ClusterTopologyRefreshOptions clusterTopologyRefreshOptions = ClusterTopologyRefreshOptions.builder().enableAllAdaptiveRefreshTriggers().enableAdaptiveRefreshTrigger(new RefreshTrigger[0]).dynamicRefreshSources(true).build();
            ClusterClientOptions clusterClientOptions = ClusterClientOptions.builder().topologyRefreshOptions(clusterTopologyRefreshOptions).build();
            Duration duration = Objects.isNull(this.redisProperties.getTimeout()) ? Duration.ofSeconds(2L) : this.redisProperties.getTimeout();
            LettuceClientConfiguration lettuceClientConfiguration = LettucePoolingClientConfiguration.builder().poolConfig(genericObjectPoolConfig).commandTimeout(duration).clientOptions(clusterClientOptions).build();
            LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
            lettuceConnectionFactory.afterPropertiesSet();
            lettuceConnectionFactory.resetConnection();
            return lettuceConnectionFactory;
        } else {
            log.info("redis单节点初始化lettuce");
            RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(this.redisProperties.getHost(), this.redisProperties.getPort());
            configuration.setPassword(this.redisProperties.getPassword());
            return new LettuceConnectionFactory(configuration);
        }
    }

    @Bean
    @ConditionalOnMissingBean
    @Primary
    public StringRedisTemplate stringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.afterPropertiesSet();
        if (this.useRedisPrefixSwitch) {
            template.setKeySerializer(new PrefixRedisSerializer(this.prefix));
        }

        template.setValueSerializer(new StringRedisSerializer());
        redisTemplate = template;
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate noPrefixStringRedisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(lettuceConnectionFactory);
        template.afterPropertiesSet();
        template.setValueSerializer(new StringRedisSerializer());
        redisTemplate = template;
        return redisTemplate;
    }


}
