package cmpt470.group7.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;

@SuppressWarnings("unused")
@Repository
public class CourseDaoImpl implements CourseDao{
	@Autowired
	private SessionFactory session;
	

	public void add(Course course) {
		session.getCurrentSession().save(course);
	}


	public void edit(Course course) {
		session.getCurrentSession().update(course);
	}


	public void delete(String courseId) {		
		session.getCurrentSession().delete(getCourse(courseId));
	}

	public Course getCourse(String courseId) {
		return (Course)session.getCurrentSession().get(Course.class, courseId);
	}

	@SuppressWarnings({ "unchecked" })
	public List<Course> getAllCourse() {
		return session.getCurrentSession().createQuery("from Course").list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Courseoption> getCourseBySemester(int semester) {
		Criteria criteria = session.getCurrentSession().createCriteria(Courseoption.class);		
		criteria.add(Restrictions.eq("courseoptionId.semesterId", semester));
        ProjectionList proList = Projections.projectionList();
        proList = proList.add(Projections.distinct(Projections.property("courseoptionId.courseId")));       
        criteria.setProjection(proList);
		return criteria.list();		
	}


	@SuppressWarnings("unchecked")
	public List<Courseoption> getCourseBySemester(int semester, String courseId, String campusId) {
		Criteria criteria = session.getCurrentSession().createCriteria(Courseoption.class);
		if (semester != -1){
			criteria.add(Restrictions.eq("courseoptionId.semesterId", semester));
		}
		if (!courseId.equals("")){
			criteria.add(Restrictions.eq("courseoptionId.courseId", courseId));
		}
		if (!campusId.equals("")){
			criteria.add(Restrictions.eq("campusId", campusId));
		}
        ProjectionList proList = Projections.projectionList();
        proList = proList.add(Projections.distinct(Projections.property("courseoptionId.courseId")));       
        criteria.setProjection(proList);
		return criteria.list();		
	}
}
