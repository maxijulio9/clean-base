package curso.usecase;

import curso.exception.ExceptionCursoWithTheSameName;
import curso.input.ICreateCursoInput;
import curso.input.IValidationAttributeCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoFactory;
import curso.modelo.CursoLevels;
import curso.output.IPersistence;

import java.time.LocalDate;

public class CursoCreateUseCase implements ICreateCursoInput {
    private IPersistence myDB;
    private IValidationAttributeCursoInput cursoWithNullAttribute;
    private IValidationAttributeCursoInput cursoWithInvalidExpirationDateInscription;
    private IValidationAttributeCursoInput cursoWithInvalidLevel;

    public CursoCreateUseCase(IPersistence myDB,
                              IValidationAttributeCursoInput cursoWithNullAttribute,
                              IValidationAttributeCursoInput cursoWithInvalidExpirationDateInscription,
                              IValidationAttributeCursoInput cursoWithInvalidLevel) {
        this.myDB = myDB;
        this.cursoWithNullAttribute = cursoWithNullAttribute;
        this.cursoWithInvalidExpirationDateInscription = cursoWithInvalidExpirationDateInscription;
        this.cursoWithInvalidLevel = cursoWithInvalidLevel;
    }
      //getInstance
        //Persistencia validar si existe antes de crear
        //Persistencia si no existe, agregarlo.

    @Override
    public void createCurso(String name, CursoLevels level, LocalDate dateExpirationInscription) throws  ExceptionCursoWithTheSameName{
        //Curso curso = Curso.getInstance(name, level, dateExpirationInscription);
        //implementamos Factory mejor
        CursoFactory cursoFactory = new CursoFactory(cursoWithNullAttribute,
                                    cursoWithInvalidExpirationDateInscription,cursoWithInvalidLevel);

        Curso curso = cursoFactory.createCursoFromFactory(name, level, dateExpirationInscription);


        if (myDB.existsCurso(curso.getName())){
            throw new ExceptionCursoWithTheSameName("El curso que intentas agregar, ya se encuentra registrado.");
        }
        myDB.saveCurso(curso);

    }

}


