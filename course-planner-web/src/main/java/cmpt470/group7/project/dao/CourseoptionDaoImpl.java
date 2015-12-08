package cmpt470.group7.project.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.CourseoptionId;

@Repository
public class CourseoptionDaoImpl implements CourseoptionDao{
	@Autowired
	private SessionFactory session;
	

	public void add(Courseoption co) {
		session.getCurrentSession().save(co);
	}


	public void edit(Courseoption co) {
		session.getCurrentSession().update(co);
	}


	public void delete(Courseoption co) {		
		session.getCurrentSession().delete(co);
	}

	public Courseoption getCourseoption(CourseoptionId coId) {
		return (Courseoption)session.getCurrentSession().get(Courseoption.class, coId);
	}

	@SuppressWarnings({ "unchecked" })
	public List<Courseoption> getAllCourseoption() {
		return session.getCurrentSession().createQuery("from Courseoption").list();
	}
	

	@SuppressWarnings("unchecked")
	public List<Courseoption> getCourseoptionBySemester(int semester) {
		Criteria criteria = session.getCurrentSession().createCriteria(Courseoption.class);		
		criteria.add(Restrictions.eq("courseoptionId.semesterId", semester));
		return criteria.list();			
	}


	@SuppressWarnings("unchecked")
	public List<Courseoption> getCourseoptionBySemesterAndCourseId(int semester, String courseId) {
		Criteria criteria = session.getCurrentSession().createCriteria(Courseoption.class);		
		criteria.add(Restrictions.eq("courseoptionId.semesterId", semester));
		criteria.add(Restrictions.eq("courseoptionId.courseId", courseId));
		return criteria.list();
	}
}
