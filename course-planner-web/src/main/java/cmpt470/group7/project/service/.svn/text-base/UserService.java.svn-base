package cmpt470.group7.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cmpt470.group7.project.dao.CourseDao;
import cmpt470.group7.project.dao.CourseDaoImpl;
import cmpt470.group7.project.dao.CourseoptionDao;
import cmpt470.group7.project.dao.CourseoptionDaoImpl;
import cmpt470.group7.project.dao.UserDao;
import cmpt470.group7.project.dao.UserDaoImpl;
import cmpt470.group7.project.model.Course;
import cmpt470.group7.project.model.Courseoption;
import cmpt470.group7.project.model.User;

@SuppressWarnings("unused")
@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	
	@Transactional
	public void add(User user) {
		userDao.addUser(user);;
	}

	@Transactional
	public void edit(User user) {
		userDao.editUser(user);
	}

	@Transactional
	public void delete(int userId) {
		userDao.deleteUser(userId);
	}

	@Transactional
	public User getUser(int userId) {
		return userDao.findUser(userId);
	}	
}

