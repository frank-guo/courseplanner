package cmpt470.group7.project.dao;

import cmpt470.group7.project.domain.StudentJdbc;

// new student dao
public interface StudDao {
    
    StudentJdbc getStudentByLogin(String login);
    
    StudentJdbc getStudentById(Integer studentId);

}
