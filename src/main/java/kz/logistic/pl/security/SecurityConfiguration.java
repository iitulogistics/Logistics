package kz.logistic.pl.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

//  @Autowired
////  DataSource repository;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable().authorizeRequests()

      //.antMatchers("/product/add", "/product/addJson").denyAll()
      //.antMatchers("/product/add", "/product/addJson").hasAuthority("sellerCompany")
      .antMatchers("/").permitAll()
//      .antMatchers("/swagger-ui.html").authenticated()
      .and()
      .formLogin()
      .permitAll()
      .and()
      .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
  }

//  @Override
//  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    auth.jdbcAuthentication()
//      .dataSource(repository)
//      .passwordEncoder(NoOpPasswordEncoder.getInstance())
//      .usersByUsernameQuery("select username, password, active from login where username=?")
//      .authoritiesByUsernameQuery("select u.username, ur.role_name from login u inner join roles ur on u.roles_id = ur.role_id where u.username=?");
//  }
  @Bean
  public JwtAuthenticationFilter jwtAuthenticationFilter() {
    return new JwtAuthenticationFilter();
  }

}
