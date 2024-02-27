package steamworks.config.swagger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import steamworks.common.util.SystemUtil;

import java.util.Arrays;

@Slf4j
@Component
public class CustomSwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        Info info = new Info().title("코드명인 API ("+ SystemUtil.getProfiles() +")")
                .description("코드명인 Spring Boot Application");

        String key = "Bearer Authentication";
        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP).scheme("Bearer").bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER).name("Authorization");
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(key);

        return new OpenAPI()
                .components(new Components().addSecuritySchemes(key, securityScheme))
                .security(Arrays.asList(securityRequirement))
                .info(info);
    }
    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer");
    }
}
