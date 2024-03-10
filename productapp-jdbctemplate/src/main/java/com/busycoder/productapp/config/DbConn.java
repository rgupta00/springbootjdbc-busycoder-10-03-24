package com.busycoder.productapp.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ConfigurationProperties(prefix = "spring.datasource")
public class DbConn {
    private String url;
    private String password;
    private String username;
    private String driverClassName;
}
