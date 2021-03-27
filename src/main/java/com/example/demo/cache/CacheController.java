package com.example.demo.cache;

import com.example.demo.jpa.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cache")
public class CacheController {
    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @GetMapping("{id}")
    public User findUserById(@PathVariable Long id) {
        return cacheService.findUserById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user) {
        return cacheService.saveUser(user);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable Long id) {
        cacheService.deleteUserById(id);
    }
}
