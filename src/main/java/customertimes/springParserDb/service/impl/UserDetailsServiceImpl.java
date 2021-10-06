package customertimes.springParserDb.service.impl;

import customertimes.springParserDb.dao.User;
import customertimes.springParserDb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // check if need this annotation
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
         final Optional< User> user = userRepository.findByUsername(username);
         if (user.isEmpty()) {
             throw new UsernameNotFoundException("Could not find user with this username: " + username);
         }

         return new UserDetailsImpl(user.get());
    }
}
