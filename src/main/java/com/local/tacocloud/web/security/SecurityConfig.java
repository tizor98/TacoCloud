package com.local.tacocloud.web.security;

import com.local.tacocloud.database.repository.UserRepository;
import com.local.tacocloud.domain.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

   @Bean
   public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder();
   }

   @Bean
   public UserDetailsService userDetailsService(UserRepository userRepository) {
      return username -> {
         User user = userRepository.findByUsername(username);
         if(user != null) return user;

         throw new UsernameNotFoundException("User '" + username + "' not found");
      };
   }

   @Bean
   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
      return http
         .authorizeRequests()
            .antMatchers("/design", "/orders").hasRole("USER")
            .antMatchers("/register").anonymous()
            .antMatchers("/").permitAll()
         .and()
            .formLogin()
               .loginPage("/login")
               .defaultSuccessUrl("/design", true)
         .and()
            .logout()
               .logoutSuccessUrl("/")
         .and()
         .cors().and().csrf().disable()
         .build();
   }


/* This method would allow to define in memory users from the start of the app
   @Bean
   public UserDetailsService userDetailsService(PasswordEncoder encoder) {
      List<UserDetails> userDetailsList = new ArrayList<>();

      userDetailsList.add(new User("user1",
         encoder.encode("12345"),
         List.of(new SimpleGrantedAuthority("ROLE_USER"))));

      userDetailsList.add(new User("user2",
         encoder.encode("67890"),
         List.of(new SimpleGrantedAuthority("ROLE_USER"))));

      return new InMemoryUserDetailsManager(userDetailsList);
   }
*/

}
