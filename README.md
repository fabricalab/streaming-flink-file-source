# flink-file-source
[![Build Status](https://img.shields.io/travis/Fabricalab/streaming-flink-file-source.svg?branch=master&style=flat-square&logo=travis)](https://travis-ci.org/fabricalab/streaming-flink-file-source)
[![codecov](https://img.shields.io/codecov/c/github/fabricalab/streaming-flink-file-source/master?style=flat-square&logo=codecov)](https://codecov.io/gh/fabricalab/streaming-flink-file-source)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg?style=flat-square)](https://opensource.org/licenses/Apache-2.0)  


```xml
<dependency>
  <groupId>it.fabricalab.streaming</groupId>
  <artifactId>flink-file-source</artifactId>
  <version>1.0.0</version>
</dependency>
```


[Apache Flink®](https://flink.apache.org/) is a framework and distributed processing engine for stateful computations over unbounded and bounded data streams.  
This repository contains the source code for a *Source operator* that allow to retrieval file content and handling
 mutations.
You must provide a class that represent the content file so that the operator can return a tiped stream.

## Documentation
All the documentation is available on the [wiki pages](https://github.com/fabricalab/streaming-flink-file-source/wiki/Configure-the-Source-Operator).

## Disclaimer
Apache Flink, Flink®, Apache®, the squirrel logo, and the Apache feather logo are either registered trademarks or trademarks of [The Apache Software Foundation](http://apache.org).
