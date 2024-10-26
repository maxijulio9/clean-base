package ar.edu.undec.adapter.service.bootstrap;

import curso.input.ICreateCursoInput;
import curso.usecase.CursoCreateUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ICreateCursoInput createCourseInput(CreateCourseGateway repository){
        return new CursoCreateUseCase(repository);

    }

}
