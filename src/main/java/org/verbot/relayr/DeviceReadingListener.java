package org.verbot.relayr;

import java.util.List;

import org.springframework.stereotype.Component;
import org.verbot.core.Punishment;
import org.verbot.users.UserStore;

import io.relayr.java.RelayrJavaSdk;
import io.relayr.java.model.Device;
import io.relayr.java.model.User;
import io.relayr.java.model.action.Reading;
import rx.Observer;
import rx.functions.Action1;

@Component
public class DeviceReadingListener {

	private User theMotivator;
	
	private UserStore userSotre = UserStore.getInstance();
	
	public void startListening() {
		
		// Use Application token here.......
		new RelayrJavaSdk.Builder().setToken("Bearer ygN_oGRYb6rFbYCu0LipqPI1oNsC2KVL").build();

		// First Get User Details from Relayr
		RelayrJavaSdk.getUser().subscribe(new Action1<User>() {
			@Override
			public void call(User user) {
				theMotivator = user;
				System.out.println(theMotivator.getName());
				// Get the devices attached to the user
				theMotivator.getDevices().subscribe(new Observer<List<Device>>() {
					@Override
					public void onCompleted() {
						System.out.println( "OnComplete Called of Device Observer");
					}

					@Override
					public void onError(Throwable t) {
						System.out.println("Somthing went wrong while observing devices....");
						t.printStackTrace();
					}

					@Override
					public void onNext(List<Device> devices) {
						System.out.println("List of devices detected.............");
						for (Device device : devices) {
							System.out.println("Reading device..." + device.getName());
							
//							System.out.println("User associated to device..." +
//							userSotre.getConsumerBasedOnDeviceId( device.getId() ).getDisaplayName() );
							
							System.out.println( "Registering for Device reading ");
							device.subscribeToCloudReadings().subscribe(new Observer<Reading>() {
								@Override
								public void onCompleted() {
									System.out.println("Cloud Reading onComplete");
								}

								@Override
								public void onError(Throwable t) {
									t.printStackTrace();
								}

								@Override
								public void onNext(Reading reading) {									
									System.out.println("Device " + device.getId() + "Reading :" + reading);
									
									if ( reading.value.equals( "ALARM" ) )
									{
										System.out.println("ALARM recieved");
										new Thread(new Punishment( UserStore.getInstance().getSubject( device.getId() ))).start();;
									
									}
								}
							});
						}
					}
				});

			}
		});

	}

}

