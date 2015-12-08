package cmpt470.group7.project.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.CourseHistoryJdbc;

@Component
public class CourseHistoryExtractor implements
		ResultSetExtractor<CourseHistoryJdbc> {

	@Override
	public CourseHistoryJdbc extractData(ResultSet resultSet)
			throws SQLException, DataAccessException {
		CourseHistoryJdbc retVal = new CourseHistoryJdbc();

		retVal.setStudentId(resultSet.getInt(1));
		retVal.setCourseId(resultSet.getString(2));
		retVal.setGrade(resultSet.getFloat(3));

		return retVal;
	}

}
