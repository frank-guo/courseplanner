package cmpt470.group7.project.controller;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cmpt470.group7.project.dao.StudDao;
import cmpt470.group7.project.domain.StudentJdbc;
import cmpt470.group7.project.json.AuthRequest;
import cmpt470.group7.project.json.AuthResponse;
import cmpt470.group7.project.service.AuthService;
import cmpt470.group7.project.service.SessionService;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Authentication related mvc and restful controller.
 * 
 * @author yanshi
 */
@Controller
public class AuthController extends AbstractController {

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private AuthService authService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private StudDao studDao;

	/**
	 * Doing authentication by processing student login/pw in json data,
	 * successful login will get session cookie in response, and redirect page
	 * in return json, it will also indicate whether the login is successful or
	 * not, please check {@link cmpt470.group7.project.json.AuthRequest} and
	 * {@link cmpt470.group7.project.json.AuthResponse} for more details.
	 * 
	 * @param authRequest
	 *            AuthRequest json string
	 * @param request
	 *            http servlet request
	 * @param response
	 *            http servlet response
	 * @return AuthResponse json string
	 * @throws IOException
	 */
    @RequestMapping(value = "/student/authenticate", consumes = { "application/json" }, produces = { "application/json" }, method = { RequestMethod.POST })
    public @ResponseBody
    String doAuth(@RequestBody String authRequest, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        AuthRequest authReq = this.mapper.readValue(authRequest, AuthRequest.class);
        StudentJdbc studentJdbc = studDao.getStudentByLogin(authReq.getLogin());
        boolean valid = this.authService.checkCredential(authReq.getLogin(), authReq.getPassword(), studentJdbc);
        AuthResponse authResp = new AuthResponse();
        authResp.setSuccess(valid);
        if (valid) {
            String path = request.getContextPath() + "/course-planner.html";
            authResp.setPage(path);
            Cookie cookie = this.sessionService.generateCookie(studentJdbc.getStudentId());
            cookie.setPath(request.getContextPath()); // only to current context path
            response.addCookie(cookie);
        }
        return this.mapper.writeValueAsString(authResp);
    }

	/**
	 * Return the student login page view, if session is valid, it will redirect
	 * to course-planner.html view
	 * 
	 * @param session
	 *            cookie value
	 * @return login page view or course planner view if session is valid
	 */
    @RequestMapping(value = "/studentlogin.html", method = { RequestMethod.GET })
    public String getAuthPage(@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
        Integer id = this.getIdByCookie(session);
        if (null == id) {
            return "studentlogin";
        }
        return "redirect:course-planner.html";
    }

	/**
	 * Logout the student session, cookie value will be only removed from
	 * backend, frontend is not touched.
	 * 
	 * @param session
	 *            cookie value
	 * @return redirect to login page after logout
	 */
    @RequestMapping(value = "/studentlogout.html", method = { RequestMethod.GET })
    public String logout(@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
        this.sessionService.removeSession(session);
        return "redirect:studentlogin.html";
    }

	/**
	 * Provide course planner view, if session is valid, otherwise, it will
	 * redirect to login page.
	 * 
	 * @param session
	 *            cookie value
	 * @return course planner view or login view if session is invalid
	 */
    @RequestMapping(value = "/course-planner.html", method = { RequestMethod.GET })
    public ModelAndView getCoursePlanner(@CookieValue(value = SessionService.SESSION_COOKIE_NAME, required = false) String session) {
    	Integer studentId = this.getIdByCookie(session);
    	if (null == studentId) {
            return new ModelAndView("redirect:studentlogin.html");
        }
    	StudentJdbc stu = this.studDao.getStudentById(studentId);
        ModelAndView mav = new ModelAndView("coursePlan_Prod");
        mav.addObject("student", stu);
        return mav;
    }

    @Override
    protected SessionService getSessionService() {
        return this.sessionService;
    }

}
