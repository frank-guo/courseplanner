package cmpt470.group7.project.dao;

import java.util.List;

import cmpt470.group7.project.domain.CourseOptionJdbc;
import cmpt470.group7.project.domain.CoursePlanJdbc;

public interface CoursePlanAndOptionDao {

	/**
	 * Retrieve all course options for a specific semester.
	 * 
	 * @param semesterId
	 * @return
	 */
	List<CourseOptionJdbc> getOptions(Integer semesterId);

	/**
	 * Batch insert a list of
	 * {@link cmpt470.group7.project.domain.CoursePlanJdbc}
	 * 
	 * @param coursePlanList
	 * @return true if succeed
	 */
	boolean createCoursePlans(List<CoursePlanJdbc> coursePlanList);

	/**
	 * Retrieve all planned courses for a specific semester for a specific
	 * student
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return a list of planned courses
	 */
	List<CoursePlanJdbc> getCoursePlans(Integer studentId, Integer semesterId);

	/**
	 * Retrieve all planned courses for a student for all semesters
	 * 
	 * @param studentId
	 * @return
	 */
	List<CoursePlanJdbc> getCoursePlansForAllSemesters(Integer studentId);

	/**
	 * Retrieve all planned courses for a student for semesters greater than
	 * current semester.
	 * 
	 * @param studentId
	 * @return
	 */
	List<CoursePlanJdbc> getCoursePlansForLaterSemesters(Integer studentId,
			Integer currentSemester);

	/**
	 * Delete course plans for a student for a semester.
	 * 
	 * @param studentId 
	 * @param semesterId
	 * @return
	 */
	boolean deleteCoursePlansForSemester(Integer studentId, Integer semesterId);
	
	/**
	 * Delete course plans for a student for the last semester.
	 * 
	 * @param studentId 
	
	 * @return
	 */
	boolean deleteCoursePlansForLastSemester(Integer studentId);
	
	/**
	 * Delete planned courses.
	 * 
	 * @param coursePlanList
	 *            list of course plan
	 * @return true if deletion succeeded.
	 */
	boolean deleteCoursePlans(List<CoursePlanJdbc> coursePlanList);
	
	List<CourseOptionJdbc> getCoursesFilteredByPrerequisite(Integer studentId, Integer semesterId); 
	List<CourseOptionJdbc> getCoursesFilteredByHistoryAndPlan(Integer studentId, Integer semesterId);
	/**
	 * get initial semester for a student by planned courses.
	 * 
	 * @param studentId
	 * @return semesterId 
	 */
	 String getInitialSemester(Integer studentId);	
}
