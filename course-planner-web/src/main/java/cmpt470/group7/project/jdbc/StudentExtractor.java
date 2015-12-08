package cmpt470.group7.project.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.StudentJdbc;

@Component
public class StudentExtractor implements ResultSetExtractor<StudentJdbc> {

    @Override
    public StudentJdbc extractData(ResultSet resultSet) throws SQLException, DataAccessException {
        StudentJdbc retVal = new StudentJdbc();

        retVal.setStudentId(resultSet.getInt(1));
        retVal.setLogin(resultSet.getString(2));
        retVal.setSalt(resultSet.getString(3));
        retVal.setHash(resultSet.getString(4));
        retVal.setFirstname(resultSet.getString(5));
        retVal.setLastname(resultSet.getString(6));
        retVal.setProgramName(resultSet.getString(7));
        retVal.setEmail(resultSet.getString(8));

        return retVal;
    }

}
