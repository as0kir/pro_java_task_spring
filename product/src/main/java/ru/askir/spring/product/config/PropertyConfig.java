package ru.askir.spring.product.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.datasource")
public class PropertyConfig {
    private final String className;
    private final String serverName;
    private final String databaseName;
    private final String portNumber;
    private final String user;
    private final String password;

    public PropertyConfig(String className, String serverName, String databaseName, String portNumber, String user, String password) {
        this.className = className;
        this.serverName = serverName;
        this.databaseName = databaseName;
        this.portNumber = portNumber;
        this.user = user;
        this.password = password;
    }

    public String getClassName() {
        return className;
    }

    public String getServerName() {
        return serverName;
    }

    public String getDatabaseName() {
        return databaseName;
    }

    public String getPortNumber() {
        return portNumber;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
}
