package it.fabricalab.util;

import it.fabricalab.BusinessConfiguration;
import org.apache.flink.core.fs.Path;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class JsonFileReaderTest {

    @ParameterizedTest
    @DisplayName("Check the utility method not throw exception when something went wrong..")
    @ValueSource(strings = {"dev/null", "src/test/resources/config/wrongConfig.json"})
    void wrongCases(String path) {
        assertNull(JsonFileReader.readFileWithType(new Path(path), BusinessConfiguration.class));
    }

    @Test
    @DisplayName("Check the utility method not throw exception when file doesn't exists..")
    void goodCase() {
        assertNotNull(JsonFileReader.readFileWithType(new Path("src/test/resources/config/config.json"), Object.class));
    }

}
