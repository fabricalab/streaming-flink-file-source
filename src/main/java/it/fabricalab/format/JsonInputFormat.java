package it.fabricalab.format;

import it.fabricalab.util.JsonFileReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.GenericTypeInfo;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.core.fs.FileInputSplit;
import org.apache.flink.core.fs.Path;

import java.io.IOException;
import java.io.Serializable;

@Slf4j
@RequiredArgsConstructor
public class JsonInputFormat<Pojo> extends FileInputFormat<Pojo> implements Serializable, ResultTypeQueryable<Pojo> {

    private final Class<Pojo> pojoClass;
    private Pojo element;
    private boolean sent = false;

    @Override
    public void open(FileInputSplit fileSplit) throws IOException {
        super.open(fileSplit);
        this.sent = false;
        final Path path = fileSplit.getPath();
        this.element = JsonFileReader.readFileWithType(path, this.pojoClass);
    }

    @Override
    public boolean reachedEnd() {
        return this.sent;
    }

    @Override
    public Pojo nextRecord(Pojo pojo) {
        this.sent = true;
        return this.element;
    }

    public TypeInformation<Pojo> getProducedType() {
        return new GenericTypeInfo<>(this.pojoClass);
    }

}
