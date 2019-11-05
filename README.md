# flink-file-source
[![Build Status](https://img.shields.io/travis/Fabricalab/streaming-flink-file-source.svg?branch=master&style=flat-square&logo=travis)](https://travis-ci.org/fabricalab/streaming-flink-file-source.svg?branch=master)
[![codecov](https://img.shields.io/codecov/c/github/fabricalab/streaming-flink-file-source/master?style=flat-square&logo=codecov)](https://codecov.io/gh/fabricalab/streaming-flink-file-source)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)  

[Apache Flink®](https://flink.apache.org/) is a framework and distributed processing engine for stateful computations over unbounded and bounded data streams.  
This repository contains the source code for a *Source operator* that allow to retrieval file content and handling
 mutations.
You must provide a class that represent the content file so that the operator can return a tiped stream.

## Configure the source
`JsonFileConsumerConfig<Pojo>` allow you to setup the `JsonFileConsumer<Pojo>`, the following code snippet shows how use that class to read ad a file and andling mutation:
```java
final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
final JsonFileConsumerConfig<BusinessConfiguration> config = new JsonFileConsumerConfig<>(BusinessConfiguration.class)
        .setPath("src/test/resources/config/config.json")
        .setMode(FileProcessingMode.PROCESS_ONCE);
final JsonFileConsumer<BusinessConfiguration> consumer = new JsonFileConsumer<>(config);
env.setParallelism(parallelism);
consumer.getJsonFileStream(env)
        .addSink(...);
env.execute();
```
For a complete example, [JsonFileConsumerTest](https://github.com/fabricalab/streaming-flink-file-source/blob/master/src/test/java/it/fabricalab/JsonFileConsumerTest.java) contanins unit and integration test that explain how the library works.
## Disclaimer
Apache Flink, Flink®, Apache®, the squirrel logo, and the Apache feather logo are either registered trademarks or trademarks of [The Apache Software Foundation](http://apache.org).
