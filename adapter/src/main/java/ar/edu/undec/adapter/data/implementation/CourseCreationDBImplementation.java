package ar.edu.undec.adapter.data.implementation;

import ar.edu.undec.adapter.data.crud.ICreateCourseCRUD;
import ar.edu.undec.adapter.data.model.CourseData;
import curso.modelo.Curso;
import curso.output.IPersistenceCreation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseCreationDBImplementation implements IPersistenceCreation {

    ICreateCourseCRUD createCourseCRUD;

    @Autowired
    public CourseCreationDBImplementation(ICreateCourseCRUD createCourseCRUD) {
        this.createCourseCRUD = createCourseCRUD;
    }

    @Override
    public boolean existsCurso(String nameCurso) {
        //funcionalidad buscar cursio por id
        return createCourseCRUD.existsCourseByName(nameCurso).equals(true);

    }

    @Override
    public boolean saveCurso(Curso cursoCore) {

        CourseData courseData = new CourseData();
        courseData.setName(cursoCore.getName());
        courseData.setLevel(cursoCore.getLevel());
        courseData.setDateExpirationInscription(cursoCore.getDateExpirationInscription());

        this.createCourseCRUD.save(courseData);
        return true;

    }
}
