package com.depromeet.mini_QR.Test;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import com.depromeet.mini_QR.config.PersistenceJPAConfig;
import com.depromeet.mini_QR.config.WebConfig;
import com.depromeet.mini_QR.domain.service.SeminarRoomService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class, PersistenceJPAConfig.class}, loader=AnnotationConfigWebContextLoader.class)

public class SeminarRoomTest {
	@Autowired
	SeminarRoomService sr;
	
	@Test
	public void test() throws MalformedURLException, IOException{
		String shortUrl = sr.createShortUrl("https://lvh.me/mini_QR/api/seminar/1");	
		System.out.println("asdfafa");
		System.out.println(shortUrl);
	}
	
	
	
}
