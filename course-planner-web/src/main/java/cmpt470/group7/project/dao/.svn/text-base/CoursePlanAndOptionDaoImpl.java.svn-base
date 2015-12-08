package cmpt470.group7.project.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Collections;
import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import cmpt470.group7.project.domain.CourseOptionJdbc;
import cmpt470.group7.project.domain.CoursePlanJdbc;
import cmpt470.group7.project.jdbc.CourseOptionRowMapper;
import cmpt470.group7.project.jdbc.CoursePlanRowMapper;

/**
 * CoursePlan and CouseOption DAO in together.
 */
@Component
public class CoursePlanAndOptionDaoImpl implements CoursePlanAndOptionDao {

	private static Logger LOG = LoggerFactory
			.getLogger(CoursePlanAndOptionDaoImpl.class);

	@Autowired
	private CourseOptionRowMapper courseOptionRowMapper;

	@Autowired
	private CoursePlanRowMapper coursePlanRowMapper;
	
	@Autowired
	private CourseplanDao courseplan;
	
	@Autowired
	private SessionFactory session;
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	@Qualifier("txTemplate")
	private TransactionTemplate txTemplate;

	@Override
	public List<CourseOptionJdbc> getOptions(Integer semesterId) {
		try {
			String query = "select * from courseoption where semesterId = ?";
			List<CourseOptionJdbc> list = this.jdbcTemplate.query(query,
					new Object[] { semesterId }, new int[] { Types.INTEGER },
					this.courseOptionRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getOptions(String).", e);
			return Collections.emptyList();
		}
	}

	@Override
	public boolean createCoursePlans(final List<CoursePlanJdbc> coursePlanList) {
		try {
			this.txTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					try {
						String insert = "insert into courseplan (studentId, courseId, sectionId, semesterId) values (?,?,?,?)";
						jdbcTemplate
								.batchUpdate(
										insert,
										coursePlanList,
										coursePlanList.size(),
										new ParameterizedPreparedStatementSetter<CoursePlanJdbc>() {
											@Override
											public void setValues(
													PreparedStatement ps,
													CoursePlanJdbc cpj)
													throws SQLException {
												ps.setInt(1, cpj.getStudentId());
												ps.setString(2,
														cpj.getCourseId());
												ps.setString(3,
														cpj.getSectionId());
												ps.setInt(4,
														cpj.getSemesterId());
											}
										});
					} catch (Exception e) {
						LOG.warn(
								"Encounter exception batch update, rolling back.",
								e);
						status.setRollbackOnly();
					}
				}
			});
			return true;
		} catch (TransactionException te) {
			LOG.warn("transaction rolled back for creating course plans.");
			return false;
		} catch (Exception e) {
			LOG.warn("Encounter exception in createCoursePlans(List).", e);
			return false;
		}
	}

	@Override
	public boolean deleteCoursePlansForLastSemester(final Integer studentId) {
		try {
			this.txTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					try {						
						String delete = "delete from courseplan where studentId = " + studentId 
								+ " and semesterId = "
								+ "(select * from (select max(semesterId) from courseplan) as p)";
						jdbcTemplate.update(delete);
					} catch (Exception e) {
						LOG.warn(
								"Encounter exception in deletion, rolling back.",
								e);
						status.setRollbackOnly();
					}
				}
			});
			return true;
		} catch (TransactionException te) {
			LOG.warn("transaction rolled back for creating course plans.");
			return false;
		} catch (Exception e) {
			LOG.warn("Encounter exception in createCoursePlans(List).", e);
			return false;
		}
	}
	@Override
	public boolean deleteCoursePlansForSemester(final Integer studentId,
			final Integer semesterId) {
		try {
			this.txTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(
						TransactionStatus status) {
					try {
						String delete = "delete from courseplan where studentId =? and semesterId = ?";
						jdbcTemplate.update(delete, new Object[] { studentId,
								semesterId }, new int[] { Types.INTEGER,
								Types.INTEGER });
					} catch (Exception e) {
						LOG.warn(
								"Encounter exception in deletion, rolling back.",
								e);
						status.setRollbackOnly();
					}
				}
			});
			return true;
		} catch (TransactionException te) {
			LOG.warn("transaction rolled back for creating course plans.");
			return false;
		} catch (Exception e) {
			LOG.warn("Encounter exception in createCoursePlans(List).", e);
			return false;
		}
	}

	@Override
	public List<CoursePlanJdbc> getCoursePlans(Integer studentId,
			Integer semesterId) {
		try {
			String query = "select * from courseplan where studentId = ? and semesterId = ?";
			List<CoursePlanJdbc> list = this.jdbcTemplate.query(query, new Object[] {
					studentId, semesterId }, new int[] { Types.INTEGER,
					Types.INTEGER }, this.coursePlanRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getCoursePlans().", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CoursePlanJdbc> getCoursePlansForAllSemesters(Integer studentId) {
		try {
			String query = "select * from courseplan where studentId = ?";
			List<CoursePlanJdbc> list = this.jdbcTemplate.query(query,
					new Object[] { studentId }, new int[] { Types.INTEGER },
					this.coursePlanRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getCoursePlans().", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CoursePlanJdbc> getCoursePlansForLaterSemesters(
			Integer studentId, Integer currentSemester) {
		try {
			String query = "select * from courseplan where studentId = ? and semesterId > ?";
			List<CoursePlanJdbc> list = this.jdbcTemplate.query(query, new Object[] {
					studentId, currentSemester }, new int[] { Types.INTEGER,
					Types.INTEGER }, this.coursePlanRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getCoursePlans().", e);
			return Collections.emptyList();
		}
	}

	@Override
	public List<CourseOptionJdbc> getCoursesFilteredByHistoryAndPlan(Integer studentId, Integer semesterId) {
		try {
			String query = "select a.courseId, a.sectionId, a.semesterId, a.campusId"
					     + " from courseoption a, course b"
					     + " where a.semesterId = " +  semesterId
					     + " and (a.courseId LIKE 'CMPT%' OR a.courseId = 'MACM101' "
					     + "      OR a.courseId = 'MACM201' OR a.courseId = 'MACM316'"
					     + "      OR a.courseId = 'MATH150' OR a.courseId = 'MATH151'"
					     + "      OR a.courseId = 'MATH152' OR a.courseId = 'MATH232'"
					     + "      OR a.courseId = 'MATH240')"
					     + " and a.courseId != 'CMPT415'"
					     + " and a.courseId != 'CMPT496'"
					     + " and a.courseId != 'CMPT497'"				     
					     + " and a.courseId = b.courseId"					   
					     + " and a.courseId NOT IN(select courseId from coursehistory where"
					     + " studentId = " + studentId + ")"
					     + " and a.courseId NOT IN(select courseId from courseplan where"
					     + " studentId = " + studentId + ")"
					     + " group by a.courseId, a.sectionId";	
			List<CourseOptionJdbc> list = this.jdbcTemplate.query(query, this.courseOptionRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getCoursesFilteredByHistoryAndPlan().", e);
			return Collections.emptyList();
		}
	}
	
	@Override
	public List<CourseOptionJdbc> getCoursesFilteredByPrerequisite(Integer studentId, Integer semesterId) {
		try {
			// prerequisite (or equivalents) courses should be in the course history 
			// or the course may not have any prerequisite
			String query = "select a.courseId, a.sectionId, a.semesterId, a.campusId"
					     + " from courseoption a, course b"
					     + " where a.semesterId = " +  semesterId
					     + " and (a.courseId LIKE 'CMPT%' OR a.courseId = 'MACM101' "
					     + "      OR a.courseId = 'MACM201' OR a.courseId = 'MACM316'"
					     + "      OR a.courseId = 'MATH150' OR a.courseId = 'MATH151'"
					     + "      OR a.courseId = 'MATH152' OR a.courseId = 'MATH232'"
					     + "      OR a.courseId = 'MATH240')"
					     + " and a.courseId != 'CMPT415'"
					     + " and a.courseId != 'CMPT496'"
					     + " and a.courseId != 'CMPT497'"				     
					     + " and a.courseId = b.courseId"
					     + " and (a.courseId NOT IN "					   
					     + "       (select c.course_id "
					     + "        from prerequisite c where"
					     + "        c.course_id = a.courseId and "
					     + "        c.prereq_id  NOT IN (select courseId from coursehistory where"
					     + "                             studentId = " + studentId + ") and"
					     + "        c.equi_prereq1  NOT IN (select courseId from coursehistory where"
					     + "                             studentId = " + studentId + ") and"
					     + "        c.equi_prereq2 NOT IN (select courseId from coursehistory where"
					     + "                             studentId = " + studentId + ")) OR"
					     + "      NOT EXISTS(select * from prerequisite where course_id = a.courseId))"
					     + " group by a.courseId, a.sectionId";	
			List<CourseOptionJdbc> list = this.jdbcTemplate.query(query, this.courseOptionRowMapper);
			return list;
		} catch (Exception e) {
			LOG.warn("Encounter exception in getCoursesFilteredByPrerequisite().", e);
			return Collections.emptyList();
		}
	}
	
	public boolean deleteCoursePlans(final List<CoursePlanJdbc> coursePlanList) {
		String deleteSql = "delete from 'courseplan' where studentId=:studendId";
		Integer studentId = coursePlanList.get(0).getStudentId();
		session.getCurrentSession().createQuery( deleteSql).setParameter(0, studentId).executeUpdate();		 
		return createCoursePlans(coursePlanList); 
	}
	
	public String getInitialSemester(Integer studentId)
	{
		String sql = "select MAX(semesterId) from courseplan where studentId =" +  studentId;
		Integer semesterId = jdbcTemplate.queryForObject(sql, Integer.class);
		try {
			switch(semesterId)			
			{
			case(1144): // summer already planned so we suggest fall
				return "Fall 2014";			
			case(1147):
				return "Spring 2015";			
			case(1151):
				return "Summer 2015";			
			case(1154):
				return "Fall 2015";			
			case(1157):
				return "Spring 2016";			
			case(1161):
				return "Summer 2016";
			case(1164):
				return "Fall 2016";			
			case(1167):
				return "Spring 2017";			
			default:
				return "Summer 2014"; // if nothing, we suggest next term.			
			}
		} catch (Exception e) {
			LOG.warn("Encounter exception in getInitialSemester().", e);
			return "Summer 2014";
		}
	}	
}
