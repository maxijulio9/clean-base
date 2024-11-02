package ar.edu.undec.adapter.service.bootstrap;

import curso.input.ICreateCursoInput;
import curso.input.ISearchCursoInput;
import curso.output.IPersistenceCreation;
import curso.output.IPersistenceSearch;
import curso.usecase.CursoCreateUseCase;
import curso.usecase.CursoSearchUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    public ICreateCursoInput createCourseInput(IPersistenceCreation myDB){
        return new CursoCreateUseCase(myDB);
    }

    @Bean
    public ISearchCursoInput createSearchInput(IPersistenceSearch myDB){ return new CursoSearchUseCase(myDB); }
}
