package cmpt470.group7.project.json;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CoursePlanTest {
	
	private ObjectMapper mapper = new ObjectMapper();

	// not a really useful test, but generate json to test create method
	@Test
	public void test() throws JsonProcessingException {
		CoursePlan cp = new CoursePlan();
		cp.setStudentId(1);
		List<CourseOption> selections = new ArrayList<>(2);
		CourseOption co = new CourseOption();
		co.setCourseId("CMPT102");
		co.setSectionId("D100");
		co.setSemesterId(1141);
		co.setCampusId("BRNBY");
		selections.add(co);
		CourseOption co2 = new CourseOption();
		co2.setCourseId("CMPT105W");
		co2.setSectionId("D200");
		co2.setSemesterId(1141);
		co2.setCampusId("SURRY");
		selections.add(co2);
		cp.setSelections(selections);
		String out = this.mapper.writeValueAsString(cp);
		System.out.println(out);
	}
}
