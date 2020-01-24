package it.fabricalab;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Getter
@NoArgsConstructor
@ToString
public class BusinessConfiguration implements Serializable {
    private static final long serialVersionUID = 786117322081236448L;

    private int primitive = 0;
    private Collection<String> collection = new ArrayList<>();
    private Leaf object = null;

    @NoArgsConstructor
    @Getter
    private static class Leaf {
        private int primitive = 0;
        private Collection<String> collection = new ArrayList<>();
    }

}
