package org.example.spartaboard.common.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class BoardRedisRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {
    fun addRecentPost(userId: String, postId: String) {
        val recentPostsKey = "recent_posts:$userId"
        redisTemplate.opsForList().leftPush(recentPostsKey, postId)

        redisTemplate.opsForList().trim(recentPostsKey, 0, 4)
    }

    fun getRecentPosts(userId: Long): List<String> {
        val recentPostsKey = "recent_posts:$userId"
        return redisTemplate.opsForList().range(recentPostsKey, 0, 4) ?: listOf()
    }
}