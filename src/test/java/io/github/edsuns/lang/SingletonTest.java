package io.github.edsuns.lang;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Edsuns@qq.com on 2022/6/25.
 */
class SingletonTest {

    @Test
    void get() throws ExecutionException, InterruptedException {
        Singleton<Map<String, String>> singleton = new Singleton<>() {
            @Override
            protected Map<String, String> init() {
                return new HashMap<>() {{
                    put("k1", "v1");
                    put("k2", "v2");
                }};
            }
        };

        final int threads = 10;
        ExecutorService pool = Executors.newFixedThreadPool(threads);

        List<Future<Map<String, String>>> tasks = new ArrayList<>();
        for (int i = 0; i < threads; i++) {
            tasks.add(pool.submit(singleton::get));
        }

        Map<String, String> last = tasks.get(0).get();
        for (Future<Map<String, String>> future : tasks) {
            assertSame(last, future.get());
        }

        pool.shutdown();
        assertTrue(pool.awaitTermination(10, TimeUnit.SECONDS));
    }
}