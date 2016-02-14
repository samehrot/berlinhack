package org.verbot.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/demo")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	 	@Autowired
	    UserService userService;  //Service which will do all data retrieval/manipulation work
	 	
	    @RequestMapping(value = "/user", method = RequestMethod.GET)
	    public ResponseEntity<List<User>> listAllUsers() {
	    	
	    	System.out.println(" I am here ");
	        List<User> users = userService.findAllUsers();
	        if(users.isEmpty()){
	            return new ResponseEntity<List<User>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
	        }
	        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	    }
	    
	    
	    @RequestMapping(value = "/user", method = RequestMethod.POST)
	    public ResponseEntity<Void> createUser(@RequestBody User user,    UriComponentsBuilder ucBuilder) {
	        System.out.println("Creating User " + user.getUsername());
	        if (userService.isUserExist(user)) {
	            System.out.println("A User with name " + user.getUsername() + " already exist");
	            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
	        }
	        
	       /* Consumer consumer = new Consumer();
			consumer.setDeviceId( user.getDeviceID());//"652f20b2-7039-4ddb-9673-56d896837d5e");
			consumer.setEmailAddress( user.getEmail());//"sachin.mehrotra@outlook.com");
			consumer.setDisaplayName( user.getUsername());//"Sachin Mehrotra");
			userOnboarding.onBoardUser( consumer );*/
	        
			userService.saveUser(user);
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	 
	
}