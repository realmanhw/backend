package com.yuepaijie.config;

import com.yuepaijie.kit.redis.RedisKit;
import com.yuepaijie.security.AuthFilter;
import com.yuepaijie.security.AuthOpenFilter;
import com.yuepaijie.security.AuthenticationStore;
import com.yuepaijie.security.LogoutFilter;
import com.yuepaijie.security.ParamsFilter;
import com.yuepaijie.security.UsernamePasswordAuthenticationFilter;
import com.yuepaijie.security.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;

@Configuration
@EnableGlobalMethodSecurity(jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private static final String[] WHITELIST = new String[] {
      ////所有url都能通过
      "/**",
      //用户注册接口
      "/userInfo/signUp",
      //swagger相关接口
      "/swagger-ui.html",
      "/v2/api-docs",
      "/webjars/**",
      "/swagger-resources/**"
  };

  @Autowired
  private RedisKit redisKit;

  @Autowired
  private AuthenticationStore authenticationStore;

  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    Class<org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter>
        clz = org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class;

    http.addFilterBefore(paramsFilter(), clz)
        .addFilterBefore(usernamePasswordAuthenticationFilter(), clz)
        .addFilterBefore(logoutFilter(), clz)
        // 这个是封闭式登录控制器，仅适合后台管理系统，先不用，注释掉
        // .addFilterBefore(authFilter(), clz)
        .addFilterBefore(authOpenFilter(), clz);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring().antMatchers(HttpMethod.GET, WHITELIST);
    web.httpFirewall(allowUrlEncodedSlashHttpFirewall());
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(usernamePasswordAuthenticationProvider);
  }

  private HttpFirewall allowUrlEncodedSlashHttpFirewall() {
    StrictHttpFirewall firewall = new StrictHttpFirewall();
    //url中允许分号
    firewall.setAllowSemicolon(true);
    return firewall;
  }

  private ParamsFilter paramsFilter() {
    return new ParamsFilter();
  }

  private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
    UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter(authenticationStore);
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
  }

  private LogoutFilter logoutFilter() throws Exception {
    LogoutFilter filter = new LogoutFilter(authenticationStore, redisKit);
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
  }

  /**
   * 封闭式登录控制过滤器，仅白名单中url可放行，适合后台管理系统，登录了才能使用，这里先不用
   *
   * @return
   */
  private AuthFilter authFilter() {
    return new AuthFilter(WHITELIST, authenticationStore);
  }

  /**
   * 开放式登录控制过滤器，请求都放行，但仅为登录者分配了身份，适合开放式社区网站
   */
  private AuthOpenFilter authOpenFilter() {
    return new AuthOpenFilter(authenticationStore);
  }
}
