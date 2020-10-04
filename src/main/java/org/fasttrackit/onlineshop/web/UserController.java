package org.fasttrackit.onlineshop.web;

import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.transfer.user.GetUsersRequest;
import org.fasttrackit.onlineshop.transfer.user.SaveUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserController {

    private final UserService userService;
    @Autowired
    public  UserController(UserService userService){
        this.userService =userService;
    }
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody  @Valid  SaveUserRequest request){
        User user = userService.createUser(request);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
     @GetMapping("/{id}")
    public ResponseEntity<User>getUser(@PathVariable long id){
         User user = userService.getUser(id);
         return  new ResponseEntity<>(user,HttpStatus.OK);

     }
     @GetMapping
     public  ResponseEntity <Page<User>> getUsers(GetUsersRequest request, Pageable pageable){
         Page<User> users = userService.getUsers( request, pageable);
         return ResponseEntity.ok(users);

     }
     @PutMapping("/{id}")
     public ResponseEntity<User> updateUser(@PathVariable long id,@RequestBody  @Valid  SaveUserRequest request ){
         User user = userService.updateUser(id, request);
         return  new ResponseEntity<>(user,HttpStatus.OK);
     }
     @DeleteMapping("/{id}")
     public ResponseEntity<Void> deleteUser(@PathVariable long id){
         userService.deleteUser(id);
         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
     }
}
