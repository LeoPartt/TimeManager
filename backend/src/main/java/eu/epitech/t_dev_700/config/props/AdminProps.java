package eu.epitech.t_dev_700.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "app.admin")
public record AdminProps(String password, boolean enabled) {}
