package cmpt470.group7.project.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cmpt470.group7.project.domain.CoursePlanJdbc;
import cmpt470.group7.project.exception.UnauthorizedException;
import cmpt470.group7.project.json.CourseInfo;
import cmpt470.group7.project.json.CourseInfoList;
import cmpt470.group7.project.json.CourseOption;
import cmpt470.group7.project.json.CourseOptionList;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.Student;
import cmpt470.group7.project.service.CoursePlanService;
import cmpt470.group7.project.service.CourseService;
import cmpt470.group7.project.service.CourseoptionService;
import cmpt470.group7.project.service.SessionService;
import cmpt470.group7.project.service.StudentService;
import cmpt470.group7.project.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This controller defines all json restful entry points.
 * 
 * NOTE: done cookie check for authorization for restful services, return 401
 *for invalid session or non-existing session.
 * 
 * @author yanshi
 * 
 */
@Controller
public class RestfulController extends AbstractController {

	private static Logger LOG = LoggerFactory
			.getLogger(RestfulController.class);

	@Autowired
	private CoursePlanService coursePlanService;

	@Autowired
	private CourseService courseService;

	@Autowired
	private CourseoptionService courseoptionService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private UserService userService;

	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Retrieve courser information by courseId
	 * 
	 * @param courseId
	 *            course id string
	 * @return course info {@link cmpt470.group7.project.json.CourseInfo}
	 */
	@RequestMapping({ "/login", "/" })
	public String printWelcome() {

		return "login";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String setupForm(SecurityContextHolderAwareRequestWrapper request,
			Map<String, Object> map) {
		if (request.isUserInRole("ROLE_ADMIN")) {
			Student student = new Student();
			map.put("student", student);
			map.put("studentList", studentService.getAllStudent());
			return "student";
		} 
		else {
			throw new UnauthorizedException();
		}
		
	}

	@RequestMapping(value = "/student/filteredcourses/{semester}", produces = { "application/json" },  method = { RequestMethod.GET })
    public @ResponseBody
    String getCoursesFilteredByPrerequisite(@PathVariable int semester, @CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session){
        List<CoursePlanJdbc> coList = null;
        Integer studentId = this.getIdByCookie(session);
        if (null != studentId) {
        	return this.coursePlanService.getCoursesFilteredByPrerequisiteJson(studentId, semester);
        }
        else {
        	throw new UnauthorizedException();
        }
	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {

		model.addAttribute("error", "true");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		return "login";

	}

	@RequestMapping(value = "/course/{courseId}", method = { RequestMethod.GET })
	public ModelAndView getCourseInfo(@PathVariable String courseId) {
		Course course = courseService.getCourse(courseId);
		return new ModelAndView("courseInfo", "course", course);
	}

	@RequestMapping(value = "/courseNoDscp/{courseId}", method = { RequestMethod.GET })
	public ModelAndView getCourseNoDscp(@PathVariable String courseId) {
		Course course = courseService.getCourse(courseId);
		return new ModelAndView("courseInfoNoDescp", "course", course);
	}
	
	@RequestMapping(value = "/course/{courseId}", produces = { "application/json" },
			                                      method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseInfoJson(@PathVariable String courseId) {
		Course course = courseService.getCourse(courseId);
		if (null != course) {
			List<CourseInfo> coursesJson = new ArrayList<>(1);
			String cId = course.getCourseId();
			CourseInfo c = new CourseInfo();
			c.setCourseId(cId);
			c.setTitle(courseService.getCourse(cId).getTitle());
			c.setDescription(courseService.getCourse(cId).getDescription());
			c.setWqb(courseService.getCourse(cId).getWqb());
			coursesJson.add(c);
			
			CourseInfoList json = new CourseInfoList();
			json.setCourseInfos(coursesJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		}
		return "";
		
	}

	@RequestMapping(value = "/course/semester/{semester}.json", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseBySemesterJson(@PathVariable int semester) {
		List<Courseoption> coList = null;
		coList = courseService.getCourseBySemester(semester);
		if (null != coList && !coList.isEmpty()) {
			List<CourseInfo> coursesJson = new ArrayList<>(coList.size());
			for (Object o : coList) {
				String cId = o.toString();
				CourseInfo c = new CourseInfo();
				c.setCourseId(cId);
				c.setTitle(courseService.getCourse(cId).getTitle());
				c.setDescription(courseService.getCourse(cId).getDescription());
				c.setWqb(courseService.getCourse(cId).getWqb());
				c.setCredit(courseService.getCourse(cId).getCredit());
				coursesJson.add(c);
			}
			CourseInfoList json = new CourseInfoList();
			json.setCourseInfos(coursesJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		}
		return "";
	}
	
	@RequestMapping(value = "/coursesearch", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseByConditionsJson(HttpServletRequest request) {
		List<Courseoption> coList = null;
		int semester =-1;
		if (!request.getParameter("semesterId").equals("")){
			semester = Integer.parseInt(request.getParameter("semesterId"));
		}

		String courseId = request.getParameter("courseId");
		String campusId = request.getParameter("campusId");
		coList = courseService.getCourseBySemester(semester, courseId, campusId);
		if (null != coList && !coList.isEmpty()) {
			List<CourseInfo> coursesJson = new ArrayList<>(coList.size());
			for (Object o : coList) {
				String cId = o.toString();
				CourseInfo c = new CourseInfo();
				c.setCourseId(cId);
				c.setTitle(courseService.getCourse(cId).getTitle());
				c.setDescription(courseService.getCourse(cId).getDescription());
				c.setWqb(courseService.getCourse(cId).getWqb());
				c.setCredit(courseService.getCourse(cId).getCredit());
				coursesJson.add(c);
			}
			CourseInfoList json = new CourseInfoList();
			json.setCourseInfos(coursesJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		}
		return "";
	}

	@RequestMapping(value = "/course/semester/{semester}", method = { RequestMethod.GET })
	public ModelAndView getCourseBySemester(@PathVariable int semester) {
		List<Courseoption> coList = null;
		coList = courseService.getCourseBySemester(semester);
		ModelAndView modelAndView = new ModelAndView("LatestCourseInfo");
		modelAndView.addObject("list", coList);
		modelAndView.addObject("courseService", courseService);
		modelAndView.addObject("semesterId", semester);
		return modelAndView;
	}

	@RequestMapping(value = "/courseoption/semester/{semester}.json", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseoptionBySemesterJson(@PathVariable int semester) {
		List<Courseoption> coList = null;
		coList = courseoptionService.getCourseoptionBySemester(semester);
		if (null != coList && !coList.isEmpty()) {
			List<CourseOption> optionsJson = new ArrayList<>(coList.size());
			for (Courseoption o : coList) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseoptionId().getCourseId());
				co.setSectionId(o.getCourseoptionId().getSectionId());
				co.setSemesterId(o.getCourseoptionId().getSemesterId());
				co.setCampusId(o.getCampusId());
				optionsJson.add(co);
			}
			CourseOptionList json = new CourseOptionList();
			json.setCourseOptions(optionsJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		}
		return "";
	}
	
	@RequestMapping(value = "/courseoption/semester-course/{semester}/{courseId}", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseoptionBySemesterAndCourseId(@PathVariable int semester,
			@PathVariable String courseId) {
		List<Courseoption> coList = null;
		coList = courseoptionService.getCourseoptionBySemesterAndCoursId(semester,courseId);
		if (null != coList && !coList.isEmpty()) {
			List<CourseOption> optionsJson = new ArrayList<>(coList.size());
			for (Courseoption o : coList) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseoptionId().getCourseId());
				co.setSectionId(o.getCourseoptionId().getSectionId());
				co.setSemesterId(o.getCourseoptionId().getSemesterId());
				co.setCampusId(o.getCampusId());
				optionsJson.add(co);
			}
			CourseOptionList json = new CourseOptionList();
			json.setCourseOptions(optionsJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		}
		return "";
	}

	@RequestMapping(value = "/courseoption/semester/{semester}", method = { RequestMethod.GET })
	public ModelAndView getCourseoptionBySemester(@PathVariable int semester) {
		List<Courseoption> coList = null;
		coList = courseoptionService.getCourseoptionBySemester(semester);
		ModelAndView modelAndView = new ModelAndView("fullCourseInfoSemester");
		modelAndView.addObject("list", coList);
		modelAndView.addObject("courseService", courseService);
		return modelAndView;
	}

	@RequestMapping(value = "/student.do", method = RequestMethod.POST)
	public String doActions(@ModelAttribute Student student,
			BindingResult result, @RequestParam String action,
			Map<String, Object> map) {
		Student studentResult = new Student();
		switch (action.toLowerCase()) {
		case "add":
			studentService.add(student);
			studentResult = student;
			break;
		case "edit":
			studentService.edit(student);
			studentResult = student;
			break;
		case "delete":
			studentService.delete(student.getStudentId());
			studentResult = new Student();
			break;
		case "search":
			Student searchedStudent = studentService.getStudent(student
					.getStudentId());
			studentResult = searchedStudent != null ? searchedStudent
					: new Student();
			break;
		}
		map.put("student", studentResult);
		map.put("studentList", studentService.getAllStudent());
		return "student";
	}

	// ===========================
	// == restful json services ==
	// ===========================
	/**
	 * Retrieve the course history of a student by id.
	 * 
	 * @param session
	 *            session cookie
	 * @return course history json
	 *         {@link cmpt470.group7.project.json.CourseHistory}
	 */
	@RequestMapping(value = "/student/history", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getCourseHistory(
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getCourseHistoryJson(studentId);
		} else {
			throw new UnauthorizedException();
		}
	}

	/**
	 * Get all planable courses for a semester, excluding courses in history and
	 * planned and consider prerequisite.
	 * 
	 * @param semesterId
	 *            semester id
	 * @param session
	 *            session cookie
	 * @return selectable course json
	 */
	@RequestMapping(value = "/student/options/{semesterId}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getSelectableCourses(
			@PathVariable Integer semesterId,
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getCourseOptionsJson(studentId,
					semesterId);
		} else {
			throw new UnauthorizedException();
		}
	}
	
	/**
	 * Get all plannable courses for a semester for a campus, excluding courses in history and
	 * planned and consider prerequisite.
	 * 
	 * @param semesterId 
	 *        semester id
	 *        campus id
	 * @param session
	 *        session cookie
	 * @return selectable course json
	 */
	@RequestMapping(value = "/student/options/{semesterId}/{campusId}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getSelectableCoursesForCampus(
			@PathVariable Integer semesterId,
			@PathVariable String campusId,
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getCourseOptionsJson(studentId,
					semesterId, campusId);
		} else {
			throw new UnauthorizedException();
		}
	}

	/**
	 * Retrieve planned courses for a specific semester of a student
	 * 
	 * @param semesterId
	 *            semester id
	 * @param session
	 *            session cookie
	 * @return selected courses json
	 */
	@RequestMapping(value = "/student/plan/{semesterId}", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getPlannedCourses(
			@PathVariable Integer semesterId,
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getPlannedCoursesJson(studentId,
					semesterId);
		} else {
			throw new UnauthorizedException();
		}
	}
	
	/**
	 * Retrieve all planned courses for a student
	 * 
	 * @param semesterId
	 *            semester id
	 * @param session
	 *            session cookie
	 * @return selected courses json
	 */
	@RequestMapping(value = "/student/allplans", produces = { "application/json" }, method = { RequestMethod.GET })
	public @ResponseBody
	String getAllPlannedCourses(
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getAllPlannedCoursesByStudentId(studentId);
		} else {
			throw new UnauthorizedException();
		}
	}

	/**
	 * Create planned courses for a semester of a student, if there are existing
	 * planned courses, this call should fail.
	 * 
	 * @param coursePlan
	 *            course select json
	 * @param session
	 *            session cookie
	 * @return true is creation is done, false means no creation happened
	 */
	@RequestMapping(value = "/student/plan", produces = { "application/json" }, consumes = { "application/json" }, method = { RequestMethod.POST })
	public @ResponseBody
	boolean createPlannedCourses(
			@RequestBody String coursePlan,
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.createCoursePlan(studentId, coursePlan);
		} else {
			throw new UnauthorizedException();
		}
	}

	/**
	 * Update planned courses for a semester of a student, can only delete the last planned semester, otherwise, it will fail.
	 * @param session
	 *            session cookie
	 * @return true if succeed
	 */
	@RequestMapping(value = "/student/plan", produces = { "application/json" }, method = { RequestMethod.DELETE })
	public @ResponseBody
	boolean deletePlannedCourses(			
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.deleteLastSemesterPlannedCourses(studentId);
		} else {
			throw new UnauthorizedException();
		}
	}
	
	
	
	/**
	 * Delete planned courses for a semester of a student
	 * 
	 * @param studentId
	 *            logged in studentId
	 * @param coursePlan
	 *            course select json
	 * @return 
	 */
	@RequestMapping(value = "/student/plan/delete/{currentTerm}", produces = { "application/json" }, consumes = { "application/json" }, method = { RequestMethod.POST })
	public @ResponseBody
	boolean deletePlannedCourses(
			@PathVariable Integer currentTerm,
			@RequestBody String coursePlan,
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.deletePlannedCoursesJson(studentId, currentTerm, coursePlan);
		} else {
			throw new UnauthorizedException();
		}

	}
	
	/**
	 * get initial semester for a student by referring planned courses
	 * 
	 * @param none
	 * @return semesterId
	 */
	@RequestMapping(value = "/student/semester", produces = { "application/text" },  method = { RequestMethod.GET })
	public @ResponseBody
	String getInitialSemester(
			@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
		Integer studentId = this.getIdByCookie(session);
		if (null != studentId) {
			return this.coursePlanService.getInitialSemester(studentId);
		} else {
			throw new UnauthorizedException();
		}
	}

	@Override
	protected SessionService getSessionService() {
		return this.sessionService;
	}
	// TODO course info json

}
