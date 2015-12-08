package cmpt470.group7.project.dao;

import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import cmpt470.group7.project.domain.StudentJdbc;
import cmpt470.group7.project.jdbc.StudentRowMapper;

@Component
public class StudDaoImpl implements StudDao {

    private static Logger LOG = LoggerFactory.getLogger(StudDaoImpl.class);

    @Autowired
    private StudentRowMapper studentRowMapper;

    @Autowired
    @Qualifier("jdbcTemplate")
    private JdbcTemplate jdbcTemplate;

    @Override
    public StudentJdbc getStudentByLogin(String login) {
    	StudentJdbc retVal = null;
        try {
            String query = "select * from student where login = ?";
            retVal = this.jdbcTemplate.queryForObject(query, new Object[] { login },
                    new int[] { Types.VARCHAR }, this.studentRowMapper);
		} catch (EmptyResultDataAccessException e) {
			// can only be empty or one, since unique key constraint
			LOG.warn("Encounter EmptyResultDataAccessException in getStudentByLogin(String) since not found.");
		} catch (Exception e) {
			LOG.warn("Encounter exception in getStudentByLogin(String).", e);
		}
        return retVal;
    }

	@Override
	public StudentJdbc getStudentById(Integer studentId) {
		StudentJdbc retVal = null;
		try {
            String query = "select * from student where studentId = ?";
            retVal = this.jdbcTemplate.queryForObject(query, new Object[] { studentId },
                    new int[] { Types.INTEGER }, this.studentRowMapper);
            return retVal;
		} catch (EmptyResultDataAccessException e) {
			// can only be empty or one, since primary key constraint
			LOG.warn("Encounter EmptyResultDataAccessException in getStudentById(Integer) since not found.");
        } catch (Exception e) {
            LOG.warn("Encounter exception in getStudentById(Integer).", e);
        }
		return retVal;
	}

}
