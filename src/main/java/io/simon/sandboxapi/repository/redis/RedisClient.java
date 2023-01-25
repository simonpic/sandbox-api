package io.simon.sandboxapi.repository.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

import java.io.IOException;

@Component
public class RedisClient {

    private final Jedis jedis;
    private final ObjectMapper objectMapper;

    public RedisClient(Jedis jedis, ObjectMapper objectMapper) {
        this.jedis = jedis;
        this.objectMapper = objectMapper;
    }

    public void putValue(String key, Object object) throws JsonProcessingException {
        byte[] bytes = objectMapper.writeValueAsBytes(object);
        jedis.set(key.getBytes(), bytes);
    }

    public boolean keyExists(String key) {
        return jedis.exists(key.getBytes());
    }

    public <T> T getValue(String key, Class<T> klass) throws IOException {
        byte[] bytes = jedis.get(key.getBytes());
        return objectMapper.readValue(bytes, klass);
    }
}
