package curso.usecase;

import curso.exception.ExceptionCursoErrorInPersistence;
import curso.exception.ExceptionCursoWithTheSameName;
import curso.input.ICreateCursoInput;
import curso.modelo.Curso;
import curso.modelo.CursoFactory;
import curso.modelo.CursoLevels;
import curso.modelo.Util;
import curso.output.IPersistence;
import curso.output.IPersistenceCreation;

import java.time.LocalDate;

public class CursoCreateUseCase implements ICreateCursoInput {
    private IPersistenceCreation myDB;

    public CursoCreateUseCase(IPersistenceCreation myDB){
        this.myDB = myDB;
    }
  

    @Override
    public Curso createCurso(String name, CursoLevels level, LocalDate dateExpirationInscription) throws  RuntimeException{
     

        Curso curso =  Curso.getInstance(name, level, dateExpirationInscription);


        if (myDB.existsCurso(curso.getName())){
            throw new ExceptionCursoWithTheSameName("El curso que intentas agregar, ya se encuentra registrado.");
        }

        myDB.saveCurso(curso);

       return curso;

    }

}


