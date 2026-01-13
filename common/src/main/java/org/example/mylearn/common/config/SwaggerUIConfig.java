package org.example.mylearn.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 要想跨模块让配置（@Component 、@Configure）生效，在common模块中，仅仅通过注解是不行的
 * 这是Spring Boot的有意设计，默认只会扫描“启动类所在包及其子包”，不会因为你在 pom.xml 里引入了一个模块依赖
 * 就自动扫描那个模块里的 @Component / @Configuration
 *
 * 解决方案（官方）：
 * 在common模块添加如下文件：common/src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
 * 文件中的内容： org.example.mylearn.common.config.SwaggerUIConfig
 * 此时，其他module在pom.xml中，通过<dependency>引入此common包，即可使当前配置生效
 * 这也正是springboot的starter机制！其他哪些starter也是这么运行的
 *
 */
@Configuration
public class SwaggerUIConfig    {
    static final Logger logger = LoggerFactory.getLogger(SwaggerUIConfig.class);
    @Bean
    public OpenAPI customOpenAPI() {
        logger.info("Loading Swagger UI ConfigRunning. Call SwaggerUIConfig::customOpenAPI()");
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("customHeader",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.APIKEY)
                                        .in(SecurityScheme.In.HEADER)
                                        .name("Authorization")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("customHeader"));
    }
}
