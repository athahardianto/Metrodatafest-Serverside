/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.id.mii.serversidemetrodatafest.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;



/**
 *
 * @author athab
 */
public class AppUserDetail implements UserDetails{
    
    private User user;

    public AppUserDetail(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // UPPERCASE, ROLE_
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getRole().forEach(userRole -> {
            String role =  "ROLE_" + userRole.getName().toUpperCase();
            authorities.add(new SimpleGrantedAuthority(role));

            userRole.getPrivileges().forEach(rolePrivilege -> {
                String privilege = rolePrivilege.getName().toUpperCase();
                authorities.add(new SimpleGrantedAuthority(privilege));
            });
        });
        
        return authorities;
        
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Verifikasi Akun
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true; // Menonaktifkan atau mengaktifkan Akun
    }
}
