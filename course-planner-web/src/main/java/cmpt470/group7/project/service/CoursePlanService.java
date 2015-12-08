package cmpt470.group7.project.service;

import java.util.List;

import cmpt470.group7.project.domain.CourseOptionJdbc;


/**
 * All course plan service calls go here
 */
public interface CoursePlanService {

	/**
	 * Retrive all history for a student
	 * 
	 * @param studentId
	 * @return
	 */
	String getCourseHistoryJson(Integer studentId);
	
	/**
	 * Retrieve all options for a student for a semester.
	 * 
	 * <p>
	 * apply filter logic here
	 * </p>
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return
	 */
	
	String getCourseOptionsJson(Integer studentId, Integer semesterId);
	
	/**
	 * Retrieve all options for a student for a semester for a campus.
	 * 
	 * <p>
	 * apply filter logic here
	 * </p>
	 * 
	 * @param studentId
	 * @param semesterId
	 * @param campusId
	 * @return
	 */
	
	String getCourseOptionsJson(Integer studentId, Integer semesterId, String campusId);
	

	/**
	 * Create course plan for a specific user with json payload
	 * 
	 * @param studentId
	 * @param jsonPayload
	 * @return true if succeed
	 */
	
	
	boolean createCoursePlan(Integer studentId, String jsonPayload);

	/**
	 * Retrieve planned courses for a student on a semester
	 * 
	 * @param studentId
	 * @param semesterId
	 * @return
	 */
	String getPlannedCoursesJson(Integer studentId, Integer semesterId);

	/**
	 * Delete planned courses for a student for a semester.
	 * 
	 * @param studentId
	 *            studentId
	 * @param semesterId
	 *            semesterId
	 * @return true if delete succeed, otherwise false
	 */
	boolean deletePlannedCourses(Integer studentId, Integer semesterId);
	
	/**
	 * Delete last semester planned courses for a student 
	 * 
	 * @param studentId
	 *            studentId
	 
	 * @return true if delete succeed, otherwise false
	 */
	boolean deleteLastSemesterPlannedCourses(Integer studentId);

	/**
	 * Retrive all course options filtered by prerequisite requirements for a semester
	 * 
	 * 
	 * @param studentId 
	 * @param semesterId
	 * @return
	 */
	String getCoursesFilteredByPrerequisiteJson(Integer studentId, Integer semesterId);

	
	List<CourseOptionJdbc> getCoursesFilteredByPrerequisite(Integer studentId, Integer semesterId);
	List<CourseOptionJdbc> getCoursesFilteredByHistoryAndPlan(Integer studentId, Integer semesterId);

	boolean deletePlannedCoursesJson(Integer studentId, Integer currentTerm, String coursePlan);

	String getAllPlannedCoursesByStudentId(Integer studentId);
	
	/**
	 * get initial semester for a student by referring planned courses.
	 * 
	 * @param studentId
	 * @return semesterId 
	 */
	String getInitialSemester(Integer studentId);


}
