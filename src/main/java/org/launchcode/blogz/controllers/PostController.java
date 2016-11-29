package org.launchcode.blogz.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {

		// TODO - implement newPost

		//get request parameters

		//validate parameters

		//if valid, create new POst

		//if not valid, send them back to the form with an error message

		//implement post model and post dao to save

		return "redirect:index"; // TODO - this redirect should go to the new post's page
	}
	//curly braces represent the dynamic urls or request handlers here
		//handles request like /blog/chris/5
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {

		// TODO - implement singlePost

		//get parameters are already above

		//get the given post

		//past the given post into the template

		return "post";
	}


	@RequestMapping(value = "/blog/{username}", method = RequestMethod.GET)
	public String userPosts(@PathVariable String username, Model model) {

		// TODO - implement userPosts

		//get all the posts

		//pass the posts into the template

		//model.addAttribute("name", someObject)
		//(name of the attribute, object, string of integer, or even a list that is defined right above)
		return "blog";
	}

}
