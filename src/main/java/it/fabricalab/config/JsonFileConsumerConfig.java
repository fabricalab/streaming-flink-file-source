package it.fabricalab.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.flink.streaming.api.functions.source.FileProcessingMode;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
@RequiredArgsConstructor
public class JsonFileConsumerConfig<Pojo> implements Serializable {
    private static final long serialVersionUID = 8211326474047944609L;

    private final Class<Pojo> inputFormatClass;
    private String operatorName = "Reading Configuration from File";
    private String uid = "ReadingConfigurationFromFile";
    private String path;
    private FileProcessingMode mode = FileProcessingMode.PROCESS_CONTINUOUSLY;
    private long interval = 1000;
    private int parallelism = 1;

}
