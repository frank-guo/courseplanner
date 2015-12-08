package cmpt470.group7.project.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpt470.group7.project.model.Courseplan;
import cmpt470.group7.project.model.CourseplanId;

@Repository
public class CourseplanDaoImpl implements CourseplanDao{
	@Autowired
	private SessionFactory session;
	

	public void add(Courseplan cp) {
		session.getCurrentSession().save(cp);
	}


	public void edit(Courseplan cp) {
		session.getCurrentSession().update(cp);
	}


	public void delete(Courseplan cp) {
		session.getCurrentSession().delete(cp);		
	}
	
	public boolean deleteCoursePlans(List<Courseplan> cpList){
		for (int i=0; i<cpList.size(); i++){
			delete(cpList.get(i));
		}
		return true;		
	}

	public Courseplan getCourseplan(CourseplanId cpId) {
		return (Courseplan)session.getCurrentSession().get(Courseplan.class, cpId);
	}
	

}
