package com.yuepaijie.secirity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.yuepaijie.model.vo.RestEntity;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public abstract class BaseAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private final ObjectMapper objectMapper;

  protected BaseAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
    super(requiresAuthenticationRequestMatcher);

    objectMapper = new Jackson2ObjectMapperBuilder()
        .propertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
        .serializationInclusion(JsonInclude.Include.NON_NULL)
        .build();
  }

  protected void setResponse(HttpServletResponse response, RestEntity restEntity) throws IOException {
    response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
    response.getWriter().write(objectMapper.writeValueAsString(restEntity));
  }

}
