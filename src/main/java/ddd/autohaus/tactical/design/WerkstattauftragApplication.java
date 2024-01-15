package ddd.autohaus.tactical.design;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WerkstattauftragApplication {

    public static void main(String[] args) {
        SpringApplication.run(WerkstattauftragApplication.class, args);
        System.out.println("##### WERKSTATT API STARTED #####");
    }
}