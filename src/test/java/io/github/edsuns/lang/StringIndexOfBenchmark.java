package io.github.edsuns.lang;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by Edsuns@qq.com on 2022/6/26.
 */
@BenchmarkMode(Mode.SingleShotTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Measurement(batchSize = 1000, iterations = 10)
@Warmup(batchSize = 1000, iterations = 10)
public class StringIndexOfBenchmark {

    static final String TARGET = "abcdef".repeat(5) + "A";
    static final String STR =
            "abcdef".repeat(500) + TARGET + "abcdef".repeat(10);

    @Benchmark
    public int stringsIndexOf() {
        return Strings.indexOf(STR, TARGET);
    }

    @Benchmark
    public int jdkIndexOf() {
        return STR.indexOf(TARGET);
    }
}
