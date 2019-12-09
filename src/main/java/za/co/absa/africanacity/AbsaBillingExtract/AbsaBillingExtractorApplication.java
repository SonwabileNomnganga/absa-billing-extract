package za.co.absa.africanacity.AbsaBillingExtract;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AbsaBillingExtractorApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbsaBillingExtractorApplication.class, args);
	}

}
