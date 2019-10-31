package it.fabricalab.format;

import it.fabricalab.util.JsonFileReader;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.flink.api.common.io.FileInputFormat;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.GenericTypeInfo;
import org.apache.flink.api.java.typeutils.ResultTypeQueryable;
import org.apache.flink.core.fs.FileInputSplit;

import java.io.IOException;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
public class JsonInputFormat<Pojo> extends FileInputFormat<Pojo> implements Serializable, ResultTypeQueryable {

    private Pojo element;
    private boolean sent = false;
    private final Class<Pojo> pojoClass;

    @Override
    public void open(FileInputSplit fileSplit) throws IOException {
        super.open(fileSplit);
        this.sent = false;
        this.element = JsonFileReader.readFileWithType(fileSplit.getPath(), this.pojoClass);
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
