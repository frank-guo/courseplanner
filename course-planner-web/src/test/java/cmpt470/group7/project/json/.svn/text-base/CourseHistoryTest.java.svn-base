package cmpt470.group7.project.json;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CourseHistoryTest {

	private ObjectMapper mapper = new ObjectMapper();

	@Test
	public void test() throws JsonProcessingException {
		CourseHistory ch = new CourseHistory();
		ch.setStudentId(132);
		List<CourseHistory.CourseGrade> list = new ArrayList<>();
		CourseHistory.CourseGrade item1 = new CourseHistory.CourseGrade();
		item1.setCourseId("345");
		item1.setGrade(4);
		list.add(item1);
		CourseHistory.CourseGrade item2 = new CourseHistory.CourseGrade();
		item2.setCourseId("567");
		item2.setGrade(3);
		list.add(item2);
		ch.setCourseGrades(list);
		String out = this.mapper.writeValueAsString(ch);
		System.out.println(out);
		assertTrue(out
				.contains("{\"studentId\":132,\"courseGrades\":[{\"courseId\":\"345\",\"grade\":4.0},{\"courseId\":\"567\",\"grade\":3.0}]}"));
	}
}
