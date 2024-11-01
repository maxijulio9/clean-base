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
        //funcionalidad buscar cursio por nombrer
        return createCourseCRUD.existsCourseByName(nameCurso);
    }

    @Override
    public boolean saveCurso(Curso cursoCore) {
        try {
            this.createCourseCRUD.save(CourseData.fromDomain(cursoCore));
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
