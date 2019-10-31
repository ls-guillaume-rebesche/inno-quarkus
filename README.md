# Innovation project to investigate quarkus
[https://quarkus.io/](https://quarkus.io/)

- [code-with-quarkus](code-with-quarkus) folder contains a small REST API written with quarkus
- [springboot-test](springboot-test) folder contains the same API but written with Spring boot
- [loadtester](loadtester) folder contains the load tester script

## Build and run

Quarkus project:
 - `cd code-with-quarkus`
 - compile and package native binary`./mvnw clean package -Pnative` (remove the `-Pnative` to compile a jar file)
 - run the binary `./target/code-with-quarkus-1.0.0-SNAPSHOT-runner`

Spring boot project:
 - `cd springboot-test`
 - compile and package native binary`./mvnw clean package`
 - run the jar file `java -jar target/springboot-test-0.0.1-SNAPSHOT.jar`
 
Load tester:
 - `cd springboot-test`
 - compile and package native binary`./mvnw clean package`
 - run the jar file `java -jar target/loadtester-0.0.1-SNAPSHOT.jar`

## Results

### Compilation times

| Stack | Compilation time |
| ------------- | ------------- |
| spring boot | ~5s |
| quarkus | ~5s |
| quarkus (native) | ~120s |

### Binary size

| Stack | Binary size |
| ------------- | ------------- |
| spring boot | 41mb + JRE (120mb) |
| quarkus | 323K + JRE (120mb) |
| quarkus (native) | 61mb |

### Startup time

| Stack | Startup time |
| ------------- | ------------- |
| spring boot | ~5s |
| quarkus | ~1.5s |
| quarkus (native) | ~15ms |

### Memory usage (after startup)

| Stack | Memory usage (after startup) |
| ------------- | ------------- |
| spring boot | ~530mb |
| quarkus | ~170mb |
| quarkus (native) | ~20mb |

### Memory usage (during load testing)

| Stack | Memory usage (during load testing) |
| ------------- | ------------- |
| spring boot | ~750mb |
| quarkus | ~370mb |
| quarkus (native) | ~300mb |