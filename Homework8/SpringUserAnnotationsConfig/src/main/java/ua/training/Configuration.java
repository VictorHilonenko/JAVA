package ua.training;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import ua.training.entities.User;
import ua.training.entities.UserRoles;

@ComponentScan("ua.training")
public class Configuration {

    @Bean
    public User adminBean() {
        return new User("admin", "21232f297a57a5a743894a0e4a801fc3", "Admin!", UserRoles.ADMIN);
    }

}
