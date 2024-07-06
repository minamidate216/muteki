package jp.co.gxp;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

/**
 * SPAでリロードした際に404を発生させずにindex.htmlを返却させるためのConfig
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class SpaResourceConfig implements WebMvcConfigurer {

  private final WebProperties webProperties;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry
        .addResourceHandler("/**")
        .addResourceLocations(webProperties.getResources().getStaticLocations())
        .resourceChain(webProperties.getResources().getChain().isCache())
        .addResolver(new SpaPageResourceResolver());
  }

  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/").setViewName("forward:index.html");
    registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
  }

  public static class SpaPageResourceResolver extends PathResourceResolver {

    @Override
    protected Resource getResource(String resourcePath, Resource location) throws IOException {
      // apiのパスは何もしない
      if (resourcePath.startsWith("api")) {
        return null;
      }
      if (resourcePath.startsWith("login") || resourcePath.startsWith("logout")) {
        return null;
      }
      // 通常の静的リソースのパスは、リソースをそのまま取得する
      if (resourcePath.endsWith(".css")
          || resourcePath.endsWith(".js")
          || resourcePath.endsWith("favicon.ico")
          || resourcePath.endsWith(".jpg")
          || resourcePath.endsWith(".png")
          || resourcePath.endsWith(".svg")
      ) {
        return super.getResource(resourcePath, location);
      }
      Resource resource = super.getResource(resourcePath, location);
      return resource != null ? resource : super.getResource("index.html", location);
    }
  }
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("*")
            .allowedHeaders("*");
  }
}
