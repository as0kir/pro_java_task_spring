package ru.askir.spring.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.datasource")
public class PropertyConfig {
    private String className;
    private String serverName;
    private String databaseName;
    private String portNumber;
    private String user;
    private String password;

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
