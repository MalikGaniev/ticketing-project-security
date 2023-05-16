package com.cydeo.service.impl;

import com.cydeo.config.UserPrinciple;
import com.cydeo.entity.User;
import com.cydeo.repository.UserRepository;
import com.cydeo.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

   private UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

User user=userRepository.findByUserNameAndIsDeleted(username,false);

if(user==null) {
throw new UsernameNotFoundException(username);
}

        return new UserPrinciple(user);
    }
}
