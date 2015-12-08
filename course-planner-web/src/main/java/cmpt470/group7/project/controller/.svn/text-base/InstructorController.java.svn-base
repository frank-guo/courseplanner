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
import cmpt470.group7.project.json.InstructorJSON;
import cmpt470.group7.project.json.InstructorJSONList;
import cmpt470.group7.project.json.TeachesJSON;
import cmpt470.group7.project.json.TeachesJSONList;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.Instructor;
import cmpt470.group7.project.model.Student;
import cmpt470.group7.project.model.Teaches;
import cmpt470.group7.project.service.CoursePlanService;
import cmpt470.group7.project.service.CourseService;
import cmpt470.group7.project.service.CourseoptionService;
import cmpt470.group7.project.service.InstructorService;
import cmpt470.group7.project.service.SessionService;
import cmpt470.group7.project.service.StudentService;
import cmpt470.group7.project.service.TeachesService;
import cmpt470.group7.project.service.UserService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This controller defines the json restful entry points regarding instructor.
 * 
 * NOTE: done cookie check for authorization for restful services, return 401
 *for invalid session or non-existing session.
 * 
 * @author ligangg
 * 
 */
@Controller
public class InstructorController extends AbstractController {

	private static Logger LOG = LoggerFactory
			.getLogger(RestfulController.class);

	@Autowired
	private CoursePlanService coursePlanService;
	
	@Autowired
	private TeachesService teachesService;
	
	@Autowired
	private InstructorService instructorService;

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
	
	@RequestMapping(value = "/teaches", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getCourseBySemesterJson(HttpServletRequest request) {
		String courseId = request.getParameter("courseId");
		String sectionId = request.getParameter("sectionId");
		Integer semesterId = Integer.parseInt(request.getParameter("semesterId"));
		
		List<Teaches> tList = null;
		tList = teachesService.getInstructorByCourseOption(courseId,sectionId,semesterId);
		if (null != tList && !tList.isEmpty()) {
			List<TeachesJSON> teachesJsonList = new ArrayList<>(tList.size());
			for (Teaches o : tList) {
				TeachesJSON t = new TeachesJSON();
				t.setInstructorId(o.getTeachesId().getInstructorId());
				t.setCourseId(o.getTeachesId().getCourseId());
				t.setSectionId(o.getTeachesId().getSectionId());
				t.setSemesterId(o.getTeachesId().getSemesterId());
				teachesJsonList.add(t);
			}
			TeachesJSONList json = new TeachesJSONList();
			json.setTeachesJSONs(teachesJsonList);
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
	
	@RequestMapping(value = "/course/instructor/{instructorId}", produces = { "application/json" }, method = { RequestMethod.GET })
	@ResponseBody
	public String getInstructorById(@PathVariable String instructorId) {		
		Instructor instructor = new Instructor();
		instructor = instructorService.getInsructorById(instructorId);
		InstructorJSON instructorJSON = new  InstructorJSON();
		if (null != instructor) {
			instructorJSON.setInstructorId(instructor.getInstructorId());
			instructorJSON.setName(instructor.getName());
			instructorJSON.setRating(instructor.getRating());
			instructorJSON.setEasiness(instructor.getEasiness());
			instructorJSON.setHelpfulness(instructor.getHelpfulness());
			instructorJSON.setVotes(instructor.getVotes());
			instructorJSON.setHot(instructor.getHot());
			
			List<InstructorJSON> instructorJsonList = new ArrayList<>(1);
			instructorJsonList.add(instructorJSON);
			
			InstructorJSONList json = new InstructorJSONList();
			json.setInstructorJSONs(instructorJsonList);
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
	
	@Override
	protected SessionService getSessionService() {
		return this.sessionService;
	}
	// TODO course info json
	
}
