package cmpt470.group7.project.dao;

import java.util.List;

import cmpt470.group7.project.model.Courseplan;
import cmpt470.group7.project.model.CourseplanId;

public interface CourseplanDao {
	public void add(Courseplan courseplan);
	public void edit(Courseplan courseplan);
	public void delete(Courseplan cp);
	public Courseplan getCourseplan(CourseplanId courseplanId);
}
