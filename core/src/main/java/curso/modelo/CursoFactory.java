package curso.modelo;

import curso.input.IValidationAttributeCursoInput;

import java.time.LocalDate;

public class CursoFactory {
    IValidationAttributeCursoInput  cursoWithNullAttribute;
    IValidationAttributeCursoInput  cursoWithInvalidExpirationDateInscription;
    IValidationAttributeCursoInput  cursoWithInvalidLevel;

    public CursoFactory(IValidationAttributeCursoInput cursoWithNullAttribute
            , IValidationAttributeCursoInput cursoWithInvalidExpirationDateInscription
            , IValidationAttributeCursoInput cursoWithInvalidLevel){
       this.cursoWithNullAttribute = cursoWithNullAttribute;
       this.cursoWithInvalidExpirationDateInscription = cursoWithInvalidExpirationDateInscription;
       this.cursoWithInvalidLevel  = cursoWithInvalidLevel;
    }

    public Curso createCursoFromFactory(String name, CursoLevels level, LocalDate dateExpirationInscription){
        //puedo agregar intefaz acá
       /* if (name ==null
                || level  == null
                || dateExpirationInscription ==null){
            throw new ExceptionCursoWithMissingAttributes("Debes proporcionar todos los atributos del curso a registrar.");
        }
        */
        cursoWithNullAttribute.validate(name, level, dateExpirationInscription);


        //Puedo agregaar interfaz acá
        /*
        if(dateExpirationInscription.isBefore(LocalDate.now())){
            throw new ExceptionCursoWithAInscriptionDateInvalid("El curso que intentas registrar tiene fecha de inscripción inválida.");
        }
         */
        cursoWithInvalidExpirationDateInscription.validate(name,level,dateExpirationInscription);

        //Puedo agregar interfaz acá
        /*if (level != CursoLevels.INICIAL && level != CursoLevels.MEDIO && level != CursoLevels.AVANZADO ){
            throw new ExceptionCursoWithAInvalidLevel("El curso que intentas registrar tiene un nivel inválido.");
        }
         */
        cursoWithInvalidLevel.validate(name,level,dateExpirationInscription);

        return Curso.getInstance(name, level, dateExpirationInscription);
    }
}
