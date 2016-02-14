package org.verbot.users;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.verbot.spark.SparkHandler;
import org.verbot.users.beans.Subject;

@RestController
@RequestMapping("/VirBot")
public class UserOnboardingController {
	
	@RequestMapping( value = "/hello",
			consumes = { "application/json" }, 
			produces = { "application/json" },
			method = RequestMethod.GET )
	public String hello()
	{
		return "HelloWOrld";
	}
	
	@RequestMapping( value = "/consumers",
			consumes = { "application/json" }, 
			produces = { "application/json" },
			method = RequestMethod.POST )
	public String onboardUser(@RequestBody Subject subject)
	{
		if ( null == subject || subject.getDeviceId() == null || subject.getDeviceId().isEmpty())
		{
			return "Fail";
		}
		//TODO: Do onboarding process............
		SparkHandler.instance().onboard( subject );
		UserStore.getInstance().addSubject( subject );
		
		return "Success";
	}

	@RequestMapping( value = "/consumers/{deviceId}",
			consumes = { "application/json" }, 
			produces = { "application/json" },
			method = RequestMethod.GET )
	public String getUser(@PathVariable String deviceId)
	{
		if (deviceId == null || deviceId.isEmpty())
		{
			return "Device ID is null";
		}
		//TODO: Do onboarding process............
		return UserStore.getInstance().getSubject( deviceId ).toString();
		
	}

	

}
