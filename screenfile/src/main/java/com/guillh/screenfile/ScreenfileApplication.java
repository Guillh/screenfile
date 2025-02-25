package com.guillh.screenfile;

import com.guillh.screenfile.model.SerieData;
import com.guillh.screenfile.service.ExternalApi;
import com.guillh.screenfile.service.ProcessData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenfileApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenfileApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		var externalApi = new ExternalApi();
		var json = externalApi.getData("https://www.omdbapi.com/?t=silo&apikey=df118d9d");
		System.out.println(json);
		ProcessData processData = new ProcessData();
		SerieData data = processData.getData(json, SerieData.class);
		System.out.println(data);

	}
}
