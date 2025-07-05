# Migration Guide - Spring Config Demo Upgrade

## Overview
This document outlines the changes made during the upgrade from Spring Boot 2.1.1/Java 8 to Spring Boot 3.2.2/Java 21.

## Version Changes

| Component | Previous Version | New Version |
|-----------|------------------|-------------|
| Spring Boot | 2.1.1.RELEASE | 3.2.2 |
| Spring Cloud Config | Greenwich.RC2 | 2023.0.0 |
| Java | 1.8 | 21 (compatible with 17+) |
| Maven | 3.5.4 | 3.9.6 |
| JGit | 4.8.0 | 6.8.0 |

## Breaking Changes and Fixes

### 1. Test Framework Update
**Change**: JUnit 4 → JUnit 5
**Files affected**: `SpringConfigApplicationTests.java`

**Before**:
```java
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringConfigApplicationTests {
    @Test
    public void contextLoads() {
    }
}
```

**After**:
```java
import org.junit.jupiter.api.Test;

@SpringBootTest
public class SpringConfigApplicationTests {
    @Test
    public void contextLoads() {
    }
}
```

### 2. Maven Configuration
**Change**: Updated compiler plugin and added explicit Java 21 support
**Files affected**: `pom.xml`

**Added**:
```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.12.1</version>
    <configuration>
        <source>21</source>
        <target>21</target>
    </configuration>
</plugin>
```

### 3. Spring Boot Version
**Change**: Updated parent dependency
**Files affected**: `pom.xml`

**Before**:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.1.RELEASE</version>
</parent>
```

**After**:
```xml
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.2.2</version>
</parent>
```

### 4. Spring Cloud Dependencies
**Change**: Updated to 2023.0.0 release train
**Files affected**: `pom.xml`

**Before**:
```xml
<spring-cloud.version>Greenwich.RC2</spring-cloud.version>
```

**After**:
```xml
<spring-cloud.version>2023.0.0</spring-cloud.version>
```

### 5. CI/CD Pipeline
**Change**: Updated Jenkinsfile for new Java version
**Files affected**: `Jenkinsfile`

**Added**:
```groovy
tools {
    jdk 'JDK21'
    maven 'Maven3.9'
}

environment {
    JAVA_HOME = tool 'JDK21'
    PATH = "$JAVA_HOME/bin:$PATH"
}
```

## Configuration Changes

### No Breaking Changes
The following configuration files required **no changes**:
- `application.properties` - All properties remain compatible
- `bootstrap.properties` - All Spring Cloud Config properties remain compatible
- `SpringConfigApplication.java` - Main application class remains unchanged

## Build and Run

### Prerequisites
- Java 21 (or Java 17 minimum)
- Maven 3.9+ (or use included Maven wrapper)

### Build Commands
```bash
# Clean and compile
./mvnw clean compile

# Run tests
./mvnw test

# Run the application
./mvnw spring-boot:run

# Package the application
./mvnw package
```

## Testing

### Test Coverage
- Context loading test
- Configuration server startup test
- Git repository integration test

### Test Commands
```bash
# Run all tests
./mvnw test

# Run with coverage
./mvnw test jacoco:report
```

## Compatibility Notes

1. **Minimum Java Version**: Now requires Java 17+ (configured for Java 21)
2. **Spring Boot 3.x**: Requires minimum Java 17
3. **Spring Cloud 2023.0.x**: Compatible with Spring Boot 3.x
4. **JGit 6.x**: Provides better Git repository integration

## Rollback Plan

To rollback to previous versions:
1. Revert `pom.xml` changes
2. Update test imports back to JUnit 4
3. Revert `Jenkinsfile` changes
4. Update documentation

## Performance Improvements

Expected improvements with the upgrade:
- Faster application startup
- Better memory usage
- Improved Git repository operations
- Enhanced security features

## Security Enhancements

- Updated all dependencies to latest versions
- Resolved known security vulnerabilities
- Enhanced SSL/TLS support
- Improved Git repository security
