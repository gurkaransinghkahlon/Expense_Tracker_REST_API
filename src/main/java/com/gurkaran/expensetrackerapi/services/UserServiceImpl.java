package com.gurkaran.expensetrackerapi.services;

import com.gurkaran.expensetrackerapi.domain.User;
import com.gurkaran.expensetrackerapi.exceptions.EtAuthException;
import com.gurkaran.expensetrackerapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.regex.Pattern;

@Service   //as a bean
@Transactional //DB transaction and commit
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository; //not actual implementation (hence decoupling)
    @Override
    public User validateUser(String email, String password) throws EtAuthException {
        if(email != null ) email = email.toLowerCase();
        return userRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public User registerUser(String firstName, String lastName, String email, String password) throws EtAuthException {
      System.out.println("I am here");
      Pattern pattern = Pattern.compile("^(.+)@(.+)$");
      if(email != null ) email = email.toLowerCase();
      if(!pattern.matcher(email).matches()){
          throw new EtAuthException("Invalid email format");
      }
      Integer count = userRepository.getCountByEmail(email);
      if(count>0) {throw new EtAuthException("Email already in use");}
      Integer userId = userRepository.create(firstName, lastName, email, password);
      return userRepository.findById(userId);
    }
}
