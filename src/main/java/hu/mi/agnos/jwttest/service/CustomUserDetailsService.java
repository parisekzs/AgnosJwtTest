package hu.mi.agnos.jwttest.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import hu.mi.agnos.user.model.AgnosUser;
import hu.mi.agnos.user.repository.PropertiesAgnosUserRepository;
import org.springframework.beans.factory.annotation.Value;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Value("${user.properties.uri}")
    private String configurationURI;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        PropertiesAgnosUserRepository agnosUserRepository = new PropertiesAgnosUserRepository(configurationURI);
        AgnosUser agnosUser = agnosUserRepository.findByName(username);
        if (agnosUser == null || !agnosUser.isEnabled()) {
            throw new UsernameNotFoundException(username);
        }
        return new UserDetailsImpl(agnosUser);
    }


}
