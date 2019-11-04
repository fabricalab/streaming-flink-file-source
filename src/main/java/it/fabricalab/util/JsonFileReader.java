package it.fabricalab.util;

import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.MapperFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JsonFileReader {
    private static final ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    private  JsonFileReader() {}

    public static <Pojo> Pojo readFileWithType(final Path path, final Class<Pojo> pojoClass) throws IOException {
        final FileSystem fs = FileSystem.get(path.toUri());
        return mapper.readValue(fs.open(path), pojoClass);
    }

}
