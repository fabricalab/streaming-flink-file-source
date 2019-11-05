package it.fabricalab.operator;

import it.fabricalab.config.JsonFileConsumerConfig;
import it.fabricalab.format.JsonInputFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

@RequiredArgsConstructor
public class JsonFileConsumer<Pojo> {

    private final JsonFileConsumerConfig<Pojo> config;
    private SingleOutputStreamOperator<Pojo> configurationStream;

    public SingleOutputStreamOperator<Pojo> getJsonFileStream(final StreamExecutionEnvironment env) {
        if (this.config.getPath() == null || this.config.getPath().isEmpty())
            throw new AssertionError("You must define a path");
        if (null == this.configurationStream) {
            final JsonInputFormat<Pojo> inputFormat = new JsonInputFormat<>(this.config.getInputFormatClass());
            this.configurationStream =
                    env.readFile(inputFormat, this.config.getPath(), this.config.getMode(), this.config.getInterval())
                            .name(this.config.getOperatorName())
                            .setParallelism(this.config.getParallelism());
        }
        return this.configurationStream;
    }

}
