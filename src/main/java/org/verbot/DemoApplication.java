package org.verbot;

import org.springframework.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.verbot.core.SubjectMonitor;
import org.verbot.relayr.DeviceReadingListener;

import com.sun.research.ws.wadl.Application;
/**
 * 
 * @author GUR40174
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "org.verbot" })
@EnableAsync
@EnableScheduling
@Configuration
public class DemoApplication extends SpringBootServletInitializer{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application ){
		return application.sources(Application.class);
	}
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        ApplicationContext context = new AnnotationConfigApplicationContext(DemoApplication.class);
		DeviceReadingListener deviceListener = (DeviceReadingListener) context.getBean( "DeivceReadingListener" );
		deviceListener.startListening();

    }
    
    @Bean(name="SubjectMonitor")
	public SubjectMonitor getSubjectMonitor()
	{
		return new SubjectMonitor();
	}
    

	@Bean(name="DeivceReadingListener")
	public DeviceReadingListener getDeivceReadingListener()
	{
		return new DeviceReadingListener();
	}


}
