package ru.studentsplatform.backend.endpoint.config;


import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * Настраиваемая страница для /actuator/info.
 *
 * @author Danila K (karnacevich5323537@gmail.com) (03.08.2020).
 */
@Component
public class CustomInfoContributor implements InfoContributor {
	@Override
	public void contribute(Info.Builder builder) {
		builder.withDetail("actuator", "This is custom info indicator. You can add your application data. " +
				"You can share application persistent data from here");
	}
}
