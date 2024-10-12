package curso.modelo;

import curso.exception.ExceptionCourseEmptyString;
import curso.exception.ExceptionCursoWithAInscriptionDateInvalid;
import curso.exception.ExceptionCursoWithAInvalidLevel;
import curso.exception.ExceptionCursoWithMissingAttributes;

import java.time.LocalDate;

public class Util {

    public static void validateEmptyString(String name,String messageException) throws RuntimeException {
        if (name == null  || name.trim().isEmpty()){
            throw new ExceptionCourseEmptyString(messageException);
        }
    }

    public static void validatedateExpirationInscription(LocalDate dateExpirationInscription, String messageException) throws RuntimeException {
        if(dateExpirationInscription.isBefore(LocalDate.now()) || dateExpirationInscription == null){
            //throw new ExceptionCursoWithAInscriptionDateInvalid("El curso que intentas registrar tiene fecha de inscripción inválida.");
            throw new ExceptionCursoWithAInscriptionDateInvalid(messageException);
        }
    }
    public static void validateLevel(CursoLevels level, String messageException) throws RuntimeException {
        if ((level != CursoLevels.INICIAL && level != CursoLevels.MEDIO && level != CursoLevels.AVANZADO) || level == null  ){
            //throw new ExceptionCursoWithAInvalidLevel("El curso que intentas registrar tiene un nivel inválido.");
            throw new ExceptionCursoWithAInvalidLevel(messageException);
        }
    }




}
