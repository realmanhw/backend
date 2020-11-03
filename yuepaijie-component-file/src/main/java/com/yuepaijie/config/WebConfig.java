package com.yuepaijie.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.common.base.Charsets;
import com.yuepaijie.annoservice.CamelStyle;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

  /**
   * 资源重定向
   * @param registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    registry.addResourceHandler("swagger-ui.html").addResourceLocations(
        "classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations(
        "classpath:/META-INF/resources/webjars/");
    super.addResourceHandlers(registry);
  }

  /**
   * 传参时驼峰和下划线模式自动转换
   * @param converters
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    StringHttpMessageConverter strConverter = new StringHttpMessageConverter();
    strConverter.setDefaultCharset(Charsets.UTF_8);
    converters.add(strConverter);
    ByteArrayHttpMessageConverter bytesConverter = new ByteArrayHttpMessageConverter();
    converters.add(bytesConverter);
    converters.add(proxyCamelJsonConverter());
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
    builder.serializationInclusion(JsonInclude.Include.NON_NULL);
    builder.deserializerByType(String.class, new JsonDeserializer<String>() {
      @Override
      public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
          throws IOException, JsonProcessingException {
        return StringUtils.trim(jsonParser.getValueAsString());
      }
    });

    MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter(builder.build());
    jsonConverter.setPrettyPrint(false);
    jsonConverter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    converters.add(jsonConverter);
  }

  private AbstractHttpMessageConverter proxyCamelJsonConverter() {
    AbstractHttpMessageConverter converter = new CamelJsonConverter();
    converter.setSupportedMediaTypes(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
    return converter;
  }

  private static class CamelJsonConverter extends AbstractHttpMessageConverter {

    final MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

    @Override
    protected Object readInternal(Class clazz, HttpInputMessage inputMessage)
        throws IOException, HttpMessageNotReadableException {
      return converter.read(clazz, inputMessage);
    }

    @Override
    protected void writeInternal(Object o, HttpOutputMessage outputMessage)
        throws IOException, HttpMessageNotWritableException {
      converter.write(o, MediaType.APPLICATION_JSON_UTF8, outputMessage);
    }

    @Override
    protected boolean supports(Class clazz) {
      return AnnotationUtils.findAnnotation(clazz, CamelStyle.class) != null;
    }
  }
}
