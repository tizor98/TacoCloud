package com.local.tacocloud.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
@Document(collection = "users")
public class User implements UserDetails, Serializable {

   private static final long serialVersionUID = 1L;

   @Id
   private String id;

   private final String username;
   private final String password;
   private final String fullName;
   private final String street;
   private final String city;
   private final String state;
   private final String zip;
   private final String phoneNumber;

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      return List.of(new SimpleGrantedAuthority("ROLE_USER"));
   }

   @Override
   public boolean isAccountNonExpired() {
      return true;
   }

   @Override
   public boolean isAccountNonLocked() {
      return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
      return true;
   }

   @Override
   public boolean isEnabled() {
      return true;
   }
}
