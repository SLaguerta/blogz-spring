package org.launchcode.blogz.controllers;

import java.util.List;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController extends AbstractController {

	@RequestMapping(value = "/")
	public String index(Model model){
		
		// TODO - fetch users and pass to template
		List<User> users = userDao.findAll();
		//displays all users
		model.addAttribute("users", users);
		return "index";
	}
	
	@RequestMapping(value = "/blog")
	public String blogIndex(Model model) {
		
		// TODO - fetch posts and pass to template
		
		//get all the posts
		List<Post> posts = postDao.findAll();
		//pass the posts into the template
		model.addAttribute("posts", posts);
		return "blog";
	}
	
}
