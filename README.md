# Scrooge-Gradle-Plugin

This is a gradle plugin for compiling thrift idl to Java/Scala/... using Facebook Scrooge.
Mainly for Java, writen in Java.

usage:

`build.gradle`:
  
```  
compileScrooge {
    thriftFiles = fileTree(dir: "src/main/thrift", include: "**/*.thrift")
    dest = file("build/generated-sources/gen-java")
    opts = []    //default Java code, ["-l","scala"] for scala code
}
```
