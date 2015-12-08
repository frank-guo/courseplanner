package cmpt470.group7.project.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpt470.group7.project.dao.CourseHistoryDao;
import cmpt470.group7.project.dao.CoursePlanAndOptionDao;
import cmpt470.group7.project.dao.CourseplanDaoImpl;
import cmpt470.group7.project.domain.CourseHistoryJdbc;
import cmpt470.group7.project.domain.CourseOptionJdbc;
import cmpt470.group7.project.domain.CoursePlanJdbc;
import cmpt470.group7.project.json.CourseHistory;
import cmpt470.group7.project.json.CourseHistory.CourseGrade;
import cmpt470.group7.project.json.CourseOption;
import cmpt470.group7.project.json.CourseOptionList;
import cmpt470.group7.project.json.CoursePlan;
import cmpt470.group7.project.model.Courseplan;
import cmpt470.group7.project.model.CourseplanId;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CoursePlanServiceImpl implements CoursePlanService {

	private static Logger LOG = LoggerFactory
			.getLogger(CoursePlanServiceImpl.class);

	// serialize/deserialize json
	private ObjectMapper mapper = new ObjectMapper();

	@Autowired
	private CourseHistoryDao courseHistoryDao;

	@Autowired
	private CoursePlanAndOptionDao coursePlanOptionDao;
	
	@Autowired
	private CourseplanDaoImpl coursePlanDao;

	@Override
	public String getCourseHistoryJson(Integer studentId) {
		List<CourseHistoryJdbc> history = this.courseHistoryDao
				.getHistory(studentId);
		CourseHistory ch = new CourseHistory();
		ch.setStudentId(studentId);
		List<CourseGrade> cGrades = new ArrayList<>();
		for (CourseHistoryJdbc row : history) {
			CourseGrade cg = new CourseGrade();
			cg.setCourseId(row.getCourseId());
			cg.setGrade(row.getGrade());
			cGrades.add(cg);
		}
		ch.setCourseGrades(cGrades);
		String retVal = "";
		try {
			retVal = this.mapper.writeValueAsString(ch);
		} catch (JsonProcessingException e) {
			LOG.warn("Encounter JsonProcessingException.", e);
		}
		return retVal;
	}

	@Override
	public String getCourseOptionsJson(Integer studentId, Integer semesterId) {
		List<CourseOptionJdbc> options = this.coursePlanOptionDao
				.getOptions(semesterId);
		List<CourseHistoryJdbc> history = this.courseHistoryDao
				.getHistory(studentId);
		List<CoursePlanJdbc> allPlans = this.coursePlanOptionDao
				.getCoursePlansForAllSemesters(studentId);
		List<CourseOptionJdbc> prereqSatisfied = this.coursePlanOptionDao
				.getCoursesFilteredByPrerequisite(studentId, semesterId);
			
		// filter
		this.filterOutHistory(options, history);
		this.filterOutAllPlans(options, allPlans);
		this.filterOutPrereqNotSatisfied(options, prereqSatisfied);
		

		if (null != options && !options.isEmpty()) {
			List<CourseOption> optionsJson = new ArrayList<>(options.size());
			for (CourseOptionJdbc o : options) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseId());
				co.setSectionId(o.getSectionId());
				co.setSemesterId(o.getSemesterId());
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
		} else {
			LOG.warn("No results found for getCourseOptionsJson()");
			return "";
		}
	}

	@Override
	public String getCourseOptionsJson(Integer studentId, Integer semesterId, String campusId) {
		List<CourseOptionJdbc> options = this.coursePlanOptionDao
				.getOptions(semesterId);
		List<CourseHistoryJdbc> history = this.courseHistoryDao
				.getHistory(studentId);
		List<CoursePlanJdbc> allPlans = this.coursePlanOptionDao
				.getCoursePlansForAllSemesters(studentId);
		List<CourseOptionJdbc> prereqSatisfied = this.coursePlanOptionDao
				.getCoursesFilteredByPrerequisite(studentId, semesterId);
			
		// filter
		this.filterOutHistory(options, history);
		this.filterOutAllPlans(options, allPlans);
		this.filterOutPrereqNotSatisfied(options, prereqSatisfied);
		

		if (null != options && !options.isEmpty()) {
			List<CourseOption> optionsJson = new ArrayList<>(options.size());
			for (CourseOptionJdbc o : options) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseId());
				co.setSectionId(o.getSectionId());
				co.setSemesterId(o.getSemesterId());
				String campus = o.getCampusId();
				
				if(!o.getCampusId().equals(campusId) && !o.getCampusId().isEmpty())
					continue;
					
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
		} else {
			LOG.warn("No results found for getCourseOptionsJson()");
			return "";
		}
	}
	
	@Override
	public boolean createCoursePlan(Integer studentId, String jsonPayload) {
		CoursePlan plan = null;
		try {
			plan = this.mapper.readValue(jsonPayload, CoursePlan.class);
		} catch (IOException e) {
			LOG.warn("Encounter IOException when deserializing CoursePlan.", e);
			return false;
		}
		List<CourseOption> selections = plan.getSelections();
		if (null == selections || selections.isEmpty()) {
			LOG.warn("selections is empty, INVALID");
			return false;
		}
		// grab history for student before checking
		List<CourseHistoryJdbc> history = this.courseHistoryDao
				.getHistory(studentId);
		List<CoursePlanJdbc> allPlans = this.coursePlanOptionDao
				.getCoursePlansForAllSemesters(studentId);
		// check selections, all course options should be for only one semester,
		// otherwise it is invalid
		Integer semesterIdPivot = selections.get(0).getSemesterId();
		if (semesterIdPivot == null) {
			LOG.warn("semesterId is null, INVALID");
			return false; // TODO should be checking semester table
		}
		for (CourseOption co : selections) {
			if (!co.getSemesterId().equals(semesterIdPivot)) {
				LOG.warn("semesterIds in selection are not same, INVALID");
				return false;
			}
		}
		// check existing history
		boolean externalValidation = true;
		if (null != history && !history.isEmpty()) {
			for (CourseOption co : selections) {
				String courseId = co.getCourseId();
				boolean internalValidation = true;
				for (CourseHistoryJdbc coj : history) {
					if (coj.getCourseId().equals(courseId)) {
						internalValidation = false;
						break;
					}
				}
				if (!internalValidation) {
					externalValidation = false;
					break;
				}
			}
		}
		if (!externalValidation) {
			LOG.warn("courseId already exists in history, INVALID");
			return false;
		}
		// check existing planned
		externalValidation = true; // reset
		if (null != allPlans && !allPlans.isEmpty()) {
			for (CourseOption co : selections) {
				String courseId = co.getCourseId();
				boolean internalValidation = true;
				for (CoursePlanJdbc cpj : allPlans) {
					if (cpj.getCourseId().equals(courseId)) {
						internalValidation = false;
						break;
					}
				}
				if (!internalValidation) {
					externalValidation = false;
					break;
				}
			}
		}
		if (!externalValidation) {
			LOG.warn("courseId already exists in history, INVALID");
			return false;
		}

		List<CoursePlanJdbc> cpjList = new ArrayList<>(selections.size());
		for (CourseOption selection : selections) {
			CoursePlanJdbc cpj = new CoursePlanJdbc();
			cpj.setStudentId(studentId);
			cpj.setCourseId(selection.getCourseId());
			cpj.setSectionId(selection.getSectionId());
			cpj.setSemesterId(selection.getSemesterId());
			cpjList.add(cpj);
		}
		boolean succeed = this.coursePlanOptionDao.createCoursePlans(cpjList);
		return succeed;
	}
	
	@Override
	public String getPlannedCoursesJson(Integer studentId, Integer semesterId) {
		List<CoursePlanJdbc> plans = this.coursePlanOptionDao.getCoursePlans(
				studentId, semesterId);
		if (null != plans && !plans.isEmpty()) {
			// TODO extract to private method
			List<CourseOption> optionsJson = new ArrayList<>(plans.size());
			for (CoursePlanJdbc o : plans) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseId());
				co.setSectionId(o.getSectionId());
				co.setSemesterId(o.getSemesterId());
				optionsJson.add(co);
			}
			CoursePlan json = new CoursePlan();
			json.setStudentId(studentId);
			json.setSelections(optionsJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		} else {
			LOG.warn("No results found for getPlannedCoursesJson()");
			return "";
		}
	}
	
	@Override
	public String getAllPlannedCoursesByStudentId(Integer studentId){
		List<CoursePlanJdbc> plans = this.coursePlanOptionDao.getCoursePlansForAllSemesters(studentId);
		if (null != plans && !plans.isEmpty()) {
			// TODO extract to private method
			List<CourseOption> optionsJson = new ArrayList<>(plans.size());
			for (CoursePlanJdbc o : plans) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseId());
				co.setSectionId(o.getSectionId());
				co.setSemesterId(o.getSemesterId());
				optionsJson.add(co);
			}
			CoursePlan json = new CoursePlan();
			json.setStudentId(studentId);
			json.setSelections(optionsJson);
			String retVal = "";
			try {
				retVal = this.mapper.writeValueAsString(json);
			} catch (JsonProcessingException e) {
				LOG.warn("Encounter JsonProcessingException.", e);
			}
			return retVal;
		} else {
			LOG.warn("No results found for getPlannedCoursesJson()");
			return "";
		}		
	}

	@Override
	public boolean deletePlannedCourses(Integer studentId, Integer semesterId) {
		List<CoursePlanJdbc> future = this.coursePlanOptionDao
				.getCoursePlansForLaterSemesters(studentId, semesterId);
		if (null != future && !future.isEmpty()) {
			LOG.warn("There are future semesters which are planned, INVALID!");
			return false;
		}
		return this.coursePlanOptionDao.deleteCoursePlansForSemester(studentId,
				semesterId);
	}
	
	@Override
	public boolean deleteLastSemesterPlannedCourses(Integer studentId) {
		return this.coursePlanOptionDao.deleteCoursePlansForLastSemester(studentId);
	}
	
	@Transactional
	public boolean deletePlannedCoursesJson(Integer studentId, Integer currentTerm,
			String jsonPayload) {
		CoursePlan plan = null;
		try {
			plan = this.mapper.readValue(jsonPayload, CoursePlan.class);
		} catch (IOException e) {
			LOG.warn("Encounter IOException when deserializing CoursePlan.", e);
			return false;
		}
		// TODO validate studentId in json and in argument?
		List<CourseOption> selections = plan.getSelections();
		if (null == selections || selections.isEmpty()) {
			return false;
		}
		List<Courseplan> cpList = new ArrayList<>(selections.size());
		
		List<CoursePlanJdbc> future = this.coursePlanOptionDao
				.getCoursePlansForLaterSemesters(studentId, currentTerm);
		if (null != future && !future.isEmpty()) {
			LOG.warn("There are future semesters which are planned, INVALID!");
			return false;
		}				
		// TODO tell if the semester is the latest one
		for (CourseOption selection : selections) {
			Courseplan cp = new Courseplan();
			CourseplanId cpId = new CourseplanId();
			cpId.setCourseId(selection.getCourseId());
			cpId.setSectionId(selection.getSectionId());
			cpId.setSemesterId(selection.getSemesterId());
			cpId.setStudentId(studentId);
			cp.setCourseplanId(cpId);
			cpList.add(cp);
		}
		return this.coursePlanDao.deleteCoursePlans(cpList);
	}

	public List<CourseOptionJdbc> getCoursesFilteredByPrerequisite(
			Integer studentId, Integer semesterId) {
		return coursePlanOptionDao.getCoursesFilteredByPrerequisite(studentId,
				semesterId);
	}

	public List<CourseOptionJdbc> getCoursesFilteredByHistoryAndPlan(
			Integer studentId, Integer semesterId) {
		return coursePlanOptionDao.getCoursesFilteredByHistoryAndPlan(
				studentId, semesterId);
	}

	@Override
	public String getCoursesFilteredByPrerequisiteJson(Integer studentId, Integer semesterId){
		List<CourseOptionJdbc> courses = this.coursePlanOptionDao.getCoursesFilteredByPrerequisite(studentId, semesterId);
		if (null != courses && !courses.isEmpty()) {
			// TODO extract to private method
			List<CourseOption> optionsJson = new ArrayList<>(courses.size());
			for (CourseOptionJdbc o : courses) {
				CourseOption co = new CourseOption();
				co.setCourseId(o.getCourseId());
				co.setSectionId(o.getSectionId());
				co.setSemesterId(o.getSemesterId());
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
		} else {
			LOG.warn("No results found for getCourseOptionsJson()");
			return "";
		}
	}
	// =============================
	// == internal helper methods ==
	// =============================

	/**
	 * Filter out all courses in history from options.
	 * 
	 * @param options
	 *            CourseOptionJdbc list
	 * @param history
	 *            CourseHistoryJdbc list
	 */
	private void filterOutHistory(List<CourseOptionJdbc> options,
			List<CourseHistoryJdbc> history) {
		// invalid, return
		if (null == options || options.isEmpty()) {
			return;
		}
		if (null == history || history.isEmpty()) {
			return;
		}
		for (ListIterator<CourseOptionJdbc> iter = options.listIterator(); iter
				.hasNext();) {
			CourseOptionJdbc itemCourseOptionJdbc = iter.next();
			for (ListIterator<CourseHistoryJdbc> iterHis = history
					.listIterator(); iterHis.hasNext();) {
				CourseHistoryJdbc item = iterHis.next();
				if (itemCourseOptionJdbc.getCourseId().equals(
						item.getCourseId())) {
					iter.remove();
					break;
				}
			}
		}
	}
	
	/**
	 * Filter out all courses in all plans from options.
	 * 
	 * @param options
	 *            CourseOptionJdbc list
	 * @param allPlans
	 *            CoursePlanJdbc list
	 */
	private void filterOutAllPlans(List<CourseOptionJdbc> options,
			                       List<CoursePlanJdbc> allPlans) {
		// invalid, return
		if (null == options || options.isEmpty()) {
			return;
		}
		if (null == allPlans || allPlans.isEmpty()) {
			return;
		}
		for (ListIterator<CourseOptionJdbc> iter = options.listIterator(); iter
				.hasNext();) {
			CourseOptionJdbc itemCourseOptionJdbc = iter.next();
			for (ListIterator<CoursePlanJdbc> iterPlan = allPlans
					.listIterator(); iterPlan.hasNext();) {
				CoursePlanJdbc item = iterPlan.next();
				if (itemCourseOptionJdbc.getCourseId().equals(
						item.getCourseId())) {
					iter.remove();
					break;
				}
			}
		}
	}
	
	/**
	 * filter out all courses which has prerequisite, which he/she has not taken, from the course options.
	 * 
	 * @param options
	 *            CourseOptionJdbc list
	 * @param prereqSatisfied
	 *            CourseOptionJdbc list
	 */
	 private void filterOutPrereqNotSatisfied(List<CourseOptionJdbc> options,
			 List<CourseOptionJdbc> prereqSatisfied) {
		// invalid, return
		if (null == options || options.isEmpty()) {
			return;
		}
		if (null == prereqSatisfied || prereqSatisfied.isEmpty()) {
			return;
		}
		for (ListIterator<CourseOptionJdbc> iter = options.listIterator(); iter
				.hasNext();) {
			CourseOptionJdbc itemCourseOptionJdbc = iter.next();
			boolean bPrereqSatisfied = false;
			for (ListIterator<CourseOptionJdbc> iterPre = prereqSatisfied
					.listIterator(); iterPre.hasNext();) {
				CourseOptionJdbc item = iterPre.next();
				if (itemCourseOptionJdbc.getCourseId().equals(
						item.getCourseId())) {
					bPrereqSatisfied = true;
					break;
				}
			}
			if(!bPrereqSatisfied)
				iter.remove();
		}
	}
	 
	 @Override
	 public String getInitialSemester(Integer studentId) {
		 String semester = this.coursePlanOptionDao.getInitialSemester(studentId);
		 if (!semester.isEmpty()) {		
			return semester;
		} else {
			LOG.warn("No results found for getInitialSemester()");
			return "";
		}
	}
}
