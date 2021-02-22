package hu.mi.agnos.jwttest.service;

import hu.mi.agnos.user.model.AgnosRole;
import hu.mi.agnos.user.model.AgnosUser;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 3185970362329652822L;

    private AgnosUser agnosUser;

    public UserDetailsImpl(AgnosUser user) {
        this.agnosUser = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        Set<AgnosRole> roles = agnosUser.getRoles();
        for (AgnosRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return agnosUser.getEncodedPassword();
    }

    @Override
    public String getUsername() {
        return agnosUser.getName();
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
        return agnosUser.isEnabled();
    }

}
