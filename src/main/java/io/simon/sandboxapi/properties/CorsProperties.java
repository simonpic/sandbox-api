package io.simon.sandboxapi.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Arrays;
import java.util.List;

@ConfigurationProperties(prefix = "sandbox-api.cors")
public class CorsProperties {

    private String allowedOrigins = "";
    private String allowedMethods = "";

    private String allowedHeaders = "";

    public List<String> getAllowedOrigins() {
        String[] allowedOrigins = this.allowedOrigins.split(",");
        return Arrays.asList(allowedOrigins);
    }

    public void setAllowedOrigins(String allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public List<String> getAllowedMethods() {
        String[] allowedMethods = this.allowedMethods.split(",");
        return Arrays.asList(allowedMethods);
    }

    public void setAllowedMethods(String allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public List<String> getAllowedHeaders() {
        String[] allowedHeaders = this.allowedHeaders.split(",");
        return Arrays.asList(allowedHeaders);
    }

    public void setAllowedHeaders(String allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    @Override
    public String toString() {
        return "CorsProperties{" +
                "allowedOrigins='" + allowedOrigins + '\'' +
                ", allowedMethods='" + allowedMethods + '\'' +
                ", allowedHeaders='" + allowedHeaders + '\'' +
                '}';
    }
}
