package cmpt470.group7.project.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.CourseHistoryJdbc;

@Component
public class CourseHistoryRowMapper implements RowMapper<CourseHistoryJdbc> {

	@Autowired
	private CourseHistoryExtractor extractor;

	@Override
	public CourseHistoryJdbc mapRow(ResultSet resultSet, int rowNum)
			throws SQLException {
		return this.extractor.extractData(resultSet);
	}

}