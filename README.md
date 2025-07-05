# Spring Config Demo

A demonstration project showcasing Spring Cloud Config Server functionality with Git repository integration.

## Overview

This project demonstrates how to set up and use Spring Cloud Config Server to externalize configuration management. The config server fetches configuration properties from a Git repository and serves them to client applications.

## Project Structure

```
spring-config-demo/
├── spring-config/                 # Main Spring Boot Config Server application
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── com/config/springconfig/
│   │   │   │       └── SpringConfigApplication.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── bootstrap.properties
│   │   └── test/
│   ├── pom.xml
│   └── target/
├── config-client.properties       # Client configuration files
├── spring-client.properties
└── spring-config.properties
```

## Technologies Used

- **Spring Boot**: 2.1.1.RELEASE
- **Spring Cloud Config Server**: Greenwich.RC2
- **Java**: 1.8
- **Maven**: 3.5.4
- **JGit**: 4.8.0 (for Git repository integration)

## Features

- **Centralized Configuration Management**: Externalize application configuration using Spring Cloud Config Server
- **Git Repository Integration**: Fetch configuration from Git repositories
- **Multiple Profile Support**: Support for different environments (test, dev, prod)
- **Dynamic Configuration Updates**: Refresh configuration without restarting applications
- **RESTful API**: Access configuration via HTTP endpoints

## Configuration Details

### Config Server Configuration

The config server is configured in `bootstrap.properties`:

```properties
# Git repository URL
spring.cloud.config.server.git.uri=https://github.com/sanjayzen/spring-config-demo

# Server configuration
server.port=8980
spring.cloud.config.enabled=false
spring.cloud.config.uri=http://localhost:8080
spring.cloud.config.label=DEV
spring.application.name=config-client.properties
spring.profiles.active=test

# Management endpoints
management.endpoints.web.exposure.include=refresh
```

### Property Files

The project includes several property files for different clients:

1. **config-client.properties**: Client-specific configuration
2. **spring-client.properties**: Spring client configuration
3. **spring-config.properties**: Main config server properties

## Prerequisites

- Java 8 or higher
- Maven 3.5+
- Git (for repository operations)

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/sanjayzen/spring-config-demo.git
cd spring-config-demo
```

### 2. Build the Project

Navigate to the `spring-config` directory and build using Maven:

```bash
cd spring-config
./mvnw clean install
```

Or on Windows:

```cmd
mvnw.cmd clean install
```

### 3. Run the Config Server

```bash
./mvnw spring-boot:run
```

Or on Windows:

```cmd
mvnw.cmd spring-boot:run
```

The config server will start on port **8980**.

### 4. Access Configuration

Once the server is running, you can access configuration via REST endpoints:

- **Get configuration for an application**: `GET /config-client/test`
- **Get configuration for specific profile**: `GET /{application}/{profile}`
- **Get configuration for specific label**: `GET /{application}/{profile}/{label}`

Example URLs:
- `http://localhost:8980/config-client/test`
- `http://localhost:8980/spring-client/test`
- `http://localhost:8980/config-client/test/DEV`

## API Endpoints

The Config Server provides the following REST endpoints:

| Endpoint | Description |
|----------|-------------|
| `/{application}/{profile}` | Get configuration for application and profile |
| `/{application}/{profile}/{label}` | Get configuration for application, profile, and label |
| `/{application}-{profile}.properties` | Get properties file format |
| `/{application}-{profile}.yml` | Get YAML format |
| `/{application}-{profile}.json` | Get JSON format |

## Configuration Properties

### Application Properties (`application.properties`)

```properties
server.port=8980
```

### Bootstrap Properties (`bootstrap.properties`)

Contains the main configuration for the config server including Git repository settings, profiles, and management endpoints.

## Development

### Running Tests

```bash
./mvnw test
```

### Building for Production

```bash
./mvnw clean package -Pprod
```

## CI/CD Integration

The project includes a Jenkins pipeline configuration (`Jenkinsfile`) for continuous integration:

```groovy
pipeline {
    agent any 
    stages {
        stage('Stage 1') {
            steps {
                echo 'Hello world!' 
            }
        }
    }
}
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -am 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Create a Pull Request

## Configuration Management Best Practices

1. **Environment-specific configurations**: Use profiles for different environments
2. **Sensitive data**: Store secrets in secure configuration stores
3. **Version control**: Keep configuration files in version control
4. **Validation**: Validate configuration changes before deployment
5. **Monitoring**: Monitor configuration changes and their impact

## Troubleshooting

### Common Issues

1. **Port conflicts**: Ensure port 8980 is available
2. **Git repository access**: Verify Git repository URL and credentials
3. **Java version**: Ensure Java 8+ is installed
4. **Maven wrapper**: Use `./mvnw` instead of `mvn` for consistent builds

### Logging

Enable debug logging by adding to `application.properties`:

```properties
logging.level.org.springframework.cloud.config=DEBUG
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Author

- **Sanjay** - [sanjayzen](https://github.com/sanjayzen)

## Acknowledgments

- Spring Cloud Config documentation
- Spring Boot community
- Maven wrapper for build consistency
