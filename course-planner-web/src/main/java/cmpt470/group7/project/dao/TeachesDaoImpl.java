package cmpt470.group7.project.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cmpt470.group7.project.model.Teaches;
import cmpt470.group7.project.model.TeachesId;
@Repository
public class TeachesDaoImpl implements TeachesDao {
	@Autowired
	private SessionFactory session;
	
	public void add(Teaches Teaches) {
		session.getCurrentSession().save(Teaches);
	}

	public void edit(Teaches Teaches) {
		session.getCurrentSession().update(Teaches);
	}

	public void delete(TeachesId teachesId) {
		session.getCurrentSession().delete(getTeachesById(teachesId));
	}

	@Override
	public Teaches getTeachesById(TeachesId teachesId) {
		return (Teaches) session.getCurrentSession().get(Teaches.class, teachesId);
	}

	@SuppressWarnings("unchecked")
	public List<Teaches> getInstructorByCourseOption(String courseId,
			String sectionId, Integer semesterId) {
		Criteria criteria = session.getCurrentSession().createCriteria(Teaches.class);
		criteria.add(Restrictions.eq("teachesId.courseId", courseId));
		criteria.add(Restrictions.eq("teachesId.sectionId", sectionId));
		criteria.add(Restrictions.eq("teachesId.semesterId", semesterId));       
		return criteria.list();		
	}
}

