package com.yy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2 // 使swagger2 生效
@Configuration // 配置注解，自动在本类上下文加载一些环境变量信息
public class SwaggerConfig {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.yy.api")).paths(PathSelectors.any())// 可以根据url路径设置哪些请求加入文档，忽略哪些请求
				.build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Swagger2接口测试页面").description("api接口文档整理，支持在线测试") // 描述
				.termsOfServiceUrl("http://www.cnwisdom.com/") // 网址
				.contact("yangyang") // 作者
				.version("1.0") // 版本号;
				.build();
	}
}