package com.mycode.ecommerce.shared.config;

import com.mycode.ecommerce.shared.exception.GlobalErrorWebExceptionHandler;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.codec.ServerCodecConfigurer;

@Configuration
public class ErrorHandlerConfig {
    @Bean
    @Order(-2)
    public GlobalErrorWebExceptionHandler globalErrorWebExceptionHandler(
            ErrorAttributes errorAttributes,
            WebProperties webProperties,
            ApplicationContext applicationContext,
            ServerCodecConfigurer codecConfigurer
    ) {
        GlobalErrorWebExceptionHandler handler =
                new GlobalErrorWebExceptionHandler(
                        errorAttributes,
                        webProperties,
                        applicationContext
                );
        handler.setMessageReaders(codecConfigurer.getReaders());
        handler.setMessageWriters(codecConfigurer.getWriters());
        return handler;
    }
}
