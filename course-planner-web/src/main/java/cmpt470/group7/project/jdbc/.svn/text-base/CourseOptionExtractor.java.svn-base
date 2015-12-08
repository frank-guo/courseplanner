package cmpt470.group7.project.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.CourseOptionJdbc;

@Component
public class CourseOptionExtractor implements
		ResultSetExtractor<CourseOptionJdbc> {

	@Override
	public CourseOptionJdbc extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		CourseOptionJdbc retVal = new CourseOptionJdbc();

		retVal.setCourseId(resultSet.getString(1));
		retVal.setSectionId(resultSet.getString(2));
		retVal.setSemesterId(resultSet.getInt(3));
		retVal.setCampusId(resultSet.getString(4));

		return retVal;
	}

}