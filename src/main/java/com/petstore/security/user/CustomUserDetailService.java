package com.petstore.security.user;

import com.cloudinary.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import us.ylabs.model.student.Student;
import us.ylabs.repository.student.StudentRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(StringUtils.isEmpty(userName))
            throw new UsernameNotFoundException("User name is empty");

        //if you don't use authority based security, just add empty set
        Set<GrantedAuthority> authorities = new HashSet<>();
        CustomUserDetails userDetails = new CustomUserDetails(userName, "", authorities);

        //here you can load user's data from DB or from
        //any other source and do:
        //userDetails.setFirstName(firstName);
        //userDetails.setLastName(lastName);
        Student student = studentRepository.findByEmail(userName);
        userDetails.setBio(student.getBio());
        userDetails.setEmail(student.getEmail());
        userDetails.setFirstTimeLogin(student.isFirstTimeLogin());
        userDetails.setFirstName(student.getFirstName());
        userDetails.setPhone(student.getPhone());
        userDetails.setImageUrl(student.getImageUrl());
        userDetails.setLastName(student.getLastName());
        userDetails.setId(student.getId());

        return userDetails;
    }
}
