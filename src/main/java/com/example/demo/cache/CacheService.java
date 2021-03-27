package com.example.demo.cache;

import com.example.demo.jpa.UserRepository;
import com.example.demo.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@CacheConfig(cacheNames = "user")
@Service
public class CacheService {
    private final UserRepository userRepository;

    public CacheService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Cacheable(key = "#id")
    public User findUserById(Long id) {
        log.info("查询数据库");
        return userRepository.findById(id).orElse(null);
    }

    @CachePut(key = "#result.id", unless = "#result == null")
    public User saveUser(User user) {
        log.info("更新数据库");
        return userRepository.save(user);
    }

    @CacheEvict(key = "#id")
    public void deleteUserById(Long id) {
        log.info("删除数据库");
        userRepository.deleteById(id);
    }
}
