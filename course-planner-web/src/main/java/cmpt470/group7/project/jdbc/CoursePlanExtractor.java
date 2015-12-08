package cmpt470.group7.project.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.CoursePlanJdbc;

@Component
public class CoursePlanExtractor implements ResultSetExtractor<CoursePlanJdbc> {

	@Override
	public CoursePlanJdbc extractData(ResultSet resultSet) throws SQLException,
			DataAccessException {
		CoursePlanJdbc retVal = new CoursePlanJdbc();

		retVal.setStudentId(resultSet.getInt(1));
		retVal.setCourseId(resultSet.getString(2));
		retVal.setSectionId(resultSet.getString(3));
		retVal.setSemesterId(resultSet.getInt(4));

		return retVal;
	}

}