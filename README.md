# flink-file-source
[![Build Status](https://img.shields.io/travis/Fabricalab/streaming-flink-file-source.svg?branch=master&style=flat-square&logo=travis)](https://travis-ci.org/fabricalab/streaming-flink-file-source)
[![Codecov](https://img.shields.io/codecov/c/github/fabricalab/streaming-flink-file-source/master?style=flat-square&logo=codecov)](https://codecov.io/gh/fabricalab/streaming-flink-file-source)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)
[![Maven Central](https://img.shields.io/maven-central/v/it.fabricalab.streaming/flink-file-source.svg?style=flat-square&label=maven)](https://search.maven.org/search?q=g:%22it.fabricalab.streaming%22%20AND%20a:%22flink-file-source%22)

[Apache Flink®](https://flink.apache.org/) is a framework and distributed processing engine for stateful computations over unbounded and bounded data streams.  
This open source project contains a *Source operator* that allow to retrieval file content and handling mutations.
You must provide a class that represent the content file so that the operator can return a typed stream.

### Maven Repository
Flink file source release are available from Maven Central. Snapshot builds are distributed using [OSS Sonatype](https://oss.sonatype.org/content/repositories/snapshots/it/fabricalab/streaming)

### Quick Installation
If you are using Maven you can add the flink-file-source by adding the following XML to your pom.xml file:

```xml
<dependency>
  <groupId>it.fabricalab.streaming</groupId>
  <artifactId>flink-file-source</artifactId>
  <version>1.1.0</version>
</dependency>
```

## Documentation
All the documentation is available on the [wiki pages](https://github.com/fabricalab/streaming-flink-file-source/wiki/Listen-a-file).

## Disclaimer
Apache Flink, Flink®, Apache®, the squirrel logo, and the Apache feather logo are either registered trademarks or trademarks of [The Apache Software Foundation](http://apache.org).
