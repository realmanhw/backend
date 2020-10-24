package com.yuepaijie.config;

import com.yuepaijie.dao.generated.UserAccountMapper;
import com.yuepaijie.secirity.AuthFilter;
import com.yuepaijie.secirity.LogoutFilter;
import com.yuepaijie.secirity.UsernamePasswordAuthenticationFilter;
import com.yuepaijie.secirity.UsernamePasswordAuthenticationProvider;
import javax.annotation.Resource;
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

  private static final String[] WHITELIST = new String[]{};

  @Resource
  UserAccountMapper userAccountMapper;

  @Autowired
  private UsernamePasswordAuthenticationProvider usernamePasswordAuthenticationProvider;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();

    Class<org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter>
        clz = org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class;

    http.addFilterBefore(usernamePasswordAuthenticationFilter(), clz)
        .addFilterBefore(logoutFilter(), clz)
        .addFilterBefore(authFilter(), clz);
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

  private UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
    UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter(userAccountMapper);
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
  }

  private LogoutFilter logoutFilter() throws Exception {
    LogoutFilter filter = new LogoutFilter();
    filter.setAuthenticationManager(authenticationManagerBean());
    return filter;
  }

  private AuthFilter authFilter() {
    return new AuthFilter(WHITELIST);
  }
}
