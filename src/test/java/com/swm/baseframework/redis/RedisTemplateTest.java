package com.swm.baseframework.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Test
    public void testString() {
        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "myStringkey";

        // when
        valueOperations.set(key, "123");

        // then
        String value = valueOperations.get(key);

        assertThat(value).isEqualTo("123");
    }

    @Test
    void testSet() {
        // given
        SetOperations<String, String> valueOperations = redisTemplate.opsForSet();
        String key = "mySetkey";

        // when
        valueOperations.add(key, "s", "w", "i", "m", "m");

        // then
        Set<String> members = valueOperations.members(key);
        Long size = valueOperations.size(key);

        assertThat(members).containsOnly("s", "w", "i", "m");
        assertThat(size).isEqualTo(4);
    }

    @Test
    public void testSortedSet() {
        // given
        ZSetOperations<String, String> valueOperations = redisTemplate.opsForZSet();
        String key = "mySortedSetkey";

        // when
        valueOperations.add(key, "s", 1);
        valueOperations.add(key, "w", 5);
        valueOperations.add(key, "i", 10);
        valueOperations.add(key, "m", 15);
        valueOperations.add(key, "m", 20);

        // then
        Set<String> range = valueOperations.range(key, 0, 5);
        System.out.println("range = " + Arrays.toString(range.toArray()));
//        assertThat(range).containsOnly("s", "w", "i", "m");

        Long size = valueOperations.size(key);
        System.out.println("size = " + size);
//        assertThat(size).isEqualTo(4);

        Set<String> scoreRange = valueOperations.rangeByScore(key, 10, 15);
        System.out.println("scoreRange = " + Arrays.toString(scoreRange.toArray()));
//        assertThat(range).containsOnly("i");
    }

    @Test
    void testHash() {
        // given
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "myHashKey";

        // when
        hashOperations.put(key, "swimming", "developer");

        // then
        Object value = hashOperations.get(key, "swimming");
        assertThat(value).isEqualTo("developer");

        Map<Object, Object> entries = hashOperations.entries(key);
        assertThat(entries.keySet()).containsExactly("swimming");
        assertThat(entries.values()).containsExactly("developer");

        Long size = hashOperations.size(key);
        assertThat(size).isEqualTo(entries.size());
    }

    @Test
    public void testList() {
        // given
        ListOperations<String, String> stringStringListOperations = redisTemplate.opsForList();
        String key = "myListKey";

        // when
        stringStringListOperations.rightPush(key, "s");
        stringStringListOperations.rightPush(key, "w");
        stringStringListOperations.rightPush(key, "i");
        stringStringListOperations.rightPush(key, "m");
        stringStringListOperations.rightPush(key, "m");
        stringStringListOperations.rightPushAll(key, "i", "n", "g");

        // then
        String char_1 = stringStringListOperations.index(key, 1);
        assertThat(char_1).isEqualTo("w");

        Long size = stringStringListOperations.size(key);
        assertThat(size).isEqualTo(8);

        List<String> resultRange = stringStringListOperations.range(key, 0, 7);
        assertThat(resultRange).containsOnly("s", "w", "i", "m", "m", "i", "n", "g");
    }
}
