package org.fasttrackit.onlineshop.service;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistence.UserRepository;
import org.fasttrackit.onlineshop.transfer.GetUsersRequest;
import org.fasttrackit.onlineshop.transfer.SaveUserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private static  final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
// Inversion of Control (IoC)
// Dependency Injection
@Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(SaveUserRequest request){
    LOGGER.info("Creating user :{}", request);
    User user = new User();
    user.setFristName(request.getFirstName());
    user.setLastName(request.getLastName());
   return userRepository.save(user);
    }

    public  User getUser(long id){
    LOGGER.info("Retrieving user {}", id);

        Optional<User> userOptional = userRepository.findById(id);
        return userRepository.findById(id)
                // Lambda expresion
                .orElseThrow(()-> new ResourceNotFoundException("User"+ id + "does not exist"));
    }

    public Page<User> getUsers(GetUsersRequest request, Pageable pageable){
    LOGGER.info("Retrieving users");

//     if (request.getPartialFirstName() != null && request.getPartialLastName() !=null {
  //           return userRepository.findByFristNameAndLastNameContains(
 //                        request.getPartialFirstName(), request.getPartialLastName(), pageable);
 //    } else if (request.getPartialFirstName() != null){
  //       return userRepository.findByFirstNameContains(request.getPartialFirstName(),pageable);
//     } else if ( request.getPartialLastName() !=null){
//         return userRepository.findByLastNameContains(request.getPartialLastName(),pageable);
//         }
//     return userRepository.findAll(pageable);
     return userRepository.findByOptionalCriteria(request.getPartialFirstName(),request.getPartialLastName(),pageable);
     }

    public User updateUser(long id, SaveUserRequest request){
    LOGGER.info("Updating user{}:{}", id,request);
        User existingUser = getUser(id);
        BeanUtils.copyProperties(request,existingUser);
        return userRepository.save(existingUser);

    }

    public void deleteUser(long id){
    LOGGER.info("Deleting user {}" ,id);
    userRepository.deleteById(id);
    }
}
