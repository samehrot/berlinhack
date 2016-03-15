package org.verbot.tropo;

import org.verbot.users.beans.Person;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class TropoHandler {
	
	private static TropoHandler handler = new TropoHandler();
	
	private TropoHandler(){
	}
	
	public static TropoHandler instance()
	{
		return handler;
	}
	
	public void call(final Person person, final String msg)
	{
		try {

			Client client = Client.create();

			String urlTropo = "https://api.tropo.com/1.0/sessions";
			
			WebResource webResource = client
					.resource(urlTropo);

			webResource.header("accept","application/json");
			webResource.header("content-type","application/json");

			String input = "{\"token\":\"496c664f716f526e694e76636a7547737663575275456e44535774546d69716a596744766f7a6e5171704277\","
					+ "\"numberToDial\":\""
					+ person.getPhone() + "\", \"msg\":\"Hello"+ person.getName() + "You have a voice message. " + msg + " \"}";

			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}else
				System.out.println();

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {

			e.printStackTrace();

		}
	}
	
	public void sms(final Person person, final String msg)
	{
		
	}
	
	public String getAchivementMessageUrl(final Person person)
	{
		return "http://hosting.tropo.com/5050119/www/buddy_inspired.mp3";
	}
	
	public String getPunishmentMessageUrl(final Person person)
	{
		return "http://hosting.tropo.com/5050119/www/daughter_corrective.mp3";
	}

}
