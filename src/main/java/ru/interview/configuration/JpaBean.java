package ru.interview.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.interview.service.*;

@Configuration
@EnableJpaRepositories( basePackages = {"ru.interview.repository"},
        entityManagerFactoryRef = "apossEntityManager",
        transactionManagerRef = "apossTransactionManager")
public class JpaBean {

    @Bean("UserService")
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean("QuestionService")
    public QuestionService questionService() {
        return new QuestionServiceImpl();
    }

    @Bean("StatusService")
    public StatusService statusService() {
        return new StatusServiceImpl();
    }
}
