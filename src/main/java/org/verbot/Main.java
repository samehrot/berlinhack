package org.verbot;

import org.springframework.web.client.RestTemplate;

public class Main {
	
	public static void main(String[] str)
	{
		String url = "https://api.tropo.com/1.0/sessions?action=create&"
				+ "token=0a592e47f9b2b6439f7f755fa6efa75a8f1d027f08099b10be4cd251726bba95011cc5f3af2f52049f1da1c2&numberToDial=4158510035&customerName=John+Dyer&msg=the+sky+is+falling";
		System.out.println("Time to get a call from loved ones.....");
		RestTemplate restTemplate = new RestTemplate();
		
		restTemplate.getForEntity(url, String.class);
	}

	}


