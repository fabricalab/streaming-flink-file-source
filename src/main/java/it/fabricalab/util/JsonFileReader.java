package it.fabricalab.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.core.fs.Path;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.MapperFeature;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class JsonFileReader {
    private static final ObjectMapper mapper = new ObjectMapper().configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

    private  JsonFileReader() {}

    public static <Pojo> Pojo readFileWithType(final Path path, final Class<Pojo> pojoClass) {
        try {
            final FileSystem fs = FileSystem.get(path.toUri());
            return mapper.readValue(fs.open(path), pojoClass);
        } catch (UnrecognizedPropertyException e) {
            log.error("Something went wrong during the deserialization of: {}", path.toUri());
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        } catch (Throwable e) {
            log.error("Something went wrong reading from: {}", path.toUri());
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

}
