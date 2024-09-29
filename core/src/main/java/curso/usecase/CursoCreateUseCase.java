package curso.usecase;

import curso.exception.ExceptionCursoWithTheSameName;
import curso.input.ICreateCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoFactory;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;

import java.time.LocalDate;

public class CursoCreateUseCase implements ICreateCursoInput {
    private IPersistence myDB;

    public CursoCreateUseCase(IPersistence myDB){
        this.myDB = myDB;
    }
      //getInstance
        //Persistencia validar si existe antes de crear
        //Persistencia si no existe, agregarlo.

    @Override
    public void createCurso(String name, CursoLevels level, LocalDate dateExpirationInscription) throws  RuntimeException{
        //Curso curso = Curso.getInstance(name, level, dateExpirationInscription);
        //implementamos Factory mejor
        CursoFactory cursoFactory = new CursoFactory(new CursoWithNullAttributeValidatorUseCase()
                                                    , new CursoWithInvalidExpirationDateInscriptionUseCase()
                                                    , new CursoWithInvalidLevelUseCase());

        Curso curso = cursoFactory.createCursoFromFactory(name, level, dateExpirationInscription);


        if (myDB.existsCurso(curso.getName())){
            throw new ExceptionCursoWithTheSameName("El curso que intentas agregar, ya se encuentra registrado.");
        }
        myDB.saveCurso(curso);

    }

}


