package cmpt470.group7.project.dao;

import java.util.List;

import cmpt470.group7.project.domain.CourseHistoryJdbc;

public interface CourseHistoryDao {

	/**
	 * Retrieve all (a list) course history for a student by id.
	 * 
	 * @param studentId
	 *            studentId integer
	 * @return a list of CouserHistoryJdbc Object
	 */
	List<CourseHistoryJdbc> getHistory(Integer studentId);

}
