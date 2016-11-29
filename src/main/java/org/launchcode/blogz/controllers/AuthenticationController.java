package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController extends AbstractController {

	@Autowired
	private UserDao userDao;

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupForm() {
		return "signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(HttpServletRequest request, Model model) {

		// TODO - implement signup

		//get parameters from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String verify = request.getParameter("verify");

		//validate parameters(username, password, verify)

		if(!(User.isValidUsername(username) & User.isValidPassword(password) & password.equals(verify))){
			if(!User.isValidUsername(username)){
				username = "";
				model.addAttribute("username_error", "Invalid username");
			}
			if(!User.isValidPassword(password)){
				password = "";
				model.addAttribute("password_error", "Invalid password");
			}
			if(!password.equals(verify) || verify==""){
				verify = "";
				model.addAttribute("verify_error", "Passwords don't match");
			}
			return "signup";
		}

		//if they validate, create a new user, and put them in the session
		User user = new User(username, password);
		userDao.save(user);
		HttpSession thisSession = request.getSession();
		setUserInSession(thisSession, user);

		return "redirect:blog/newpost";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {

		// TODO - implement login

		//get parameters from request
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		//get user by their username
		User user = userDao.findByUsername(username);

		//check passowrd is correct; if not redirect to the login page with error message;
		if(!user.isMatchingPassword(password)){
			model.addAttribute("error", "Incorrect password, try again");
			return "login";
		}
		//consider a helper method for the login parts above and here; log the user in if pasword validates and set the user in the session
		HttpSession thisSession = request.getSession();
		setUserInSession(thisSession, user);
		return "redirect:blog/newpost";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request){
        request.getSession().invalidate();
		return "redirect:/";
	}
}
