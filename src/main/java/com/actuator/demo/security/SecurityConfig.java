package com.actuator.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
 public class SecurityConfig extends WebSecurityConfigurerAdapter {


     @Value("${management.access.iplist}")
     private String iplist;

     @Override
     protected void configure(HttpSecurity http) throws Exception {
                 /*
                 //  允许所有用户访问"/"和"/index.html"
                 http.authorizeRequests()
                         .antMatchers("/", "/index.html").permitAll()
                         .anyRequest().authenticated()   // 其他地址的访问均需验证权限
                         .and()
                         .formLogin()
                         .loginPage("/login.html")   //  登录页
                         .failureUrl("/login-error.html").permitAll()
                         .and()
                         .logout()
                         .logoutSuccessUrl("/index.html");
                 */

                //System.out.println(iplist);
                 //得到iplist列表
                String iprule = "";
                //hasIpAddress('10.0.0.0/16') or hasIpAddress('127.0.0.1/32')
                String[] splitAddress=iplist.split(",");
                for(String ip : splitAddress){
                     //System.out.println(ip);
                     if (iprule.equals("")) {
                         iprule = "hasIpAddress('"+ip+"')";
                     } else {
                         iprule += " or hasIpAddress('"+ip+"')";
                     }
                }
               System.out.println(iprule);

         http.authorizeRequests()
                         //.antMatchers("/actuator/**").hasIpAddress("127.0.0.1")
                 //.antMatchers("/admin/**").access("hasRole('admin') and (hasIpAddress('127.0.0.1') or hasIpAddress('192.168.1.0/24') or hasIpAddress('0:0:0:0:0:0:0:1'))");
                 .antMatchers("/lhdmon/**").access(iprule)
                 .antMatchers("/home/**").hasIpAddress("192.168.1.6")
                 .anyRequest().permitAll();
             }
             
     @Autowired
     public  void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         /*
         auth.inMemoryAuthentication()
                 .withUser("laoliu").password("lhddemo").roles("ADMIN")
                 .and()
                 .withUser("laoliu2").password("lhddemo").roles("ADMIN");
         */
     }
 }