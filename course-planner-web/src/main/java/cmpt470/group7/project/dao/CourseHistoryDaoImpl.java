package cmpt470.group7.project.dao;

import java.sql.Types;
import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.CourseHistoryJdbc;
import cmpt470.group7.project.jdbc.CourseHistoryRowMapper;

@Component
public class CourseHistoryDaoImpl implements CourseHistoryDao {

	private static Logger LOG = LoggerFactory
			.getLogger(CourseHistoryDaoImpl.class);

	@Autowired
	@Qualifier("dataSource")
	private DataSource dataSource;

	@Autowired
	private CourseHistoryRowMapper courseHistoryRowMapper;

	@Override
	public List<CourseHistoryJdbc> getHistory(Integer studentId) {
		try {
			String query = "select * from coursehistory where studentId = ?";
			JdbcTemplate jdbcTemplate = new JdbcTemplate(this.dataSource);
			List<CourseHistoryJdbc> list = jdbcTemplate.query(query,
					new Object[] { studentId }, new int[] { Types.INTEGER },
					this.courseHistoryRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getHistory(Integer).",
					e);
			return Collections.emptyList();
		}
	}

}
