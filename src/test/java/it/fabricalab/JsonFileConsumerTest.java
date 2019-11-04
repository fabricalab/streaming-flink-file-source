package it.fabricalab;

import it.fabricalab.config.JsonFileConsumerConfig;
import it.fabricalab.operator.JsonFileConsumer;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class JsonFileConsumerTest {
    private final static int parallelism = 1;
    private CollectSink sink = new CollectSink();

    @Test
    @DisplayName("Reading a file from path..")
    void readFile() throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final JsonFileConsumerConfig<BusinessConfiguration> config = new JsonFileConsumerConfig<>(BusinessConfiguration.class)
                .setPath("src/test/resources/config/config.json")
                .setMode(FileProcessingMode.PROCESS_ONCE);
        final JsonFileConsumer<BusinessConfiguration> consumer = new JsonFileConsumer<>(config);

        env.setParallelism(parallelism);
        consumer.getJsonFileStream(env)
                .addSink(this.sink)
                .setParallelism(parallelism);

        System.out.println(env.getExecutionPlan());
        env.execute();

        assertNotNull(CollectSink.values.get(0));
    }

    @Test
    @DisplayName("Check that the stream are the same each time..")
    void checkStream() {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        final JsonFileConsumerConfig<BusinessConfiguration> config = new JsonFileConsumerConfig<>(BusinessConfiguration.class)
                .setPath("src/test/resources/config/config.json")
                .setMode(FileProcessingMode.PROCESS_ONCE);
        final JsonFileConsumer<BusinessConfiguration> consumer = new JsonFileConsumer<>(config);

        env.setParallelism(parallelism);

        assertEquals(consumer.getJsonFileStream(env), consumer.getJsonFileStream(env));
    }

    @ParameterizedTest
    @DisplayName("Check assertion on path..")
    @NullSource
    @EmptySource
    void checkAssertion(String path) {
        Assertions.assertThrows(AssertionError.class, () -> {
            final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            final JsonFileConsumerConfig<BusinessConfiguration> config = new JsonFileConsumerConfig<>(BusinessConfiguration.class)
                    .setPath(path)
                    .setMode(FileProcessingMode.PROCESS_ONCE);
            new JsonFileConsumer<>(config)
                    .getJsonFileStream(env);
        });
    }

    static class CollectSink implements SinkFunction<BusinessConfiguration> {
        private static final long serialVersionUID = -4819511717828201186L;

        static List<BusinessConfiguration> values = new ArrayList<>();

        @Override
        public void invoke(BusinessConfiguration value, Context context) {
            values.add(value);
        }

    }
}
