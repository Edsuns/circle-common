package io.github.edsuns.sync;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by Edsuns@qq.com on 2022/7/22.
 */
class SingleFlightTest {

    @Test
    void call() throws InterruptedException {
        final AtomicInteger query = new AtomicInteger(0);
        class Client {
            final SingleFlight single = new SingleFlight();

            void query() {
                single.call("query", () -> {
                    if (query.get() == 0) {
                        try {
                            Thread.sleep(100L);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return query.incrementAndGet();
                    }
                    return 1;
                });
            }
        }

        int clients = 3, cnt = 6, threads = clients * cnt;
        ExecutorService pool = Executors.newFixedThreadPool(threads);
        for (int i = 0; i < clients; i++) {
            Client client = new Client();
            for (int j = 0; j < cnt; j++) {
                pool.execute(client::query);
            }
        }

        pool.shutdown();
        assertTrue(pool.awaitTermination(10L, TimeUnit.SECONDS));

        assertTrue(query.get() <= clients);
    }
}