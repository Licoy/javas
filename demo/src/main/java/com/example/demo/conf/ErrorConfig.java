package com.example.demo.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

/**
 * @author licoy.cn
 * @version 2018/3/18
 */
@Component
public class ErrorConfig {

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer(){
        return configurableEmbeddedServletContainer -> {
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/error/404"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.BAD_REQUEST, "/error/400"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error/500"));
            configurableEmbeddedServletContainer
                    .addErrorPages(new ErrorPage(java.lang.Throwable.class, "/error/500"));
        };
    }

}
