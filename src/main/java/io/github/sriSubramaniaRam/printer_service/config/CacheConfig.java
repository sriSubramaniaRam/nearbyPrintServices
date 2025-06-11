package io.github.sriSubramaniaRam.printer_service.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
public class CacheConfig {

	@Bean
	public RedisCacheConfiguration redisCacheConfiguration() {
		return RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5));
	}

	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory,
			RedisCacheConfiguration redisConfiguration) {
		return RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(redisConfiguration).build();

	}
}
