package org.launchcode.blogz.controllers;

import javax.servlet.http.HttpServletRequest;

import org.launchcode.blogz.models.Post;
import org.launchcode.blogz.models.User;
import org.launchcode.blogz.models.dao.PostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PostController extends AbstractController {

	@Autowired
	private PostDao postDao;
	
	@RequestMapping(value = "/blog/newpost", method = RequestMethod.GET)
	public String newPostForm() {
		return "newpost";
	}

	@RequestMapping(value = "/blog/newpost", method = RequestMethod.POST)
	public String newPost(HttpServletRequest request, Model model) {

		// TODO - implement newPost

		//get request parameters
		String title = request.getParameter("title");
		String body = request.getParameter("body");
		
		
		//validate parameters: If not valid, send them back to form with error message
		
		if(title==null || title==""){
			model.addAttribute("error", "Title required");
			return "newpost";
		}
		if(body==null || body==""){
			model.addAttribute("error", "Body required");
			return "newpost";
		}

		//if valid, create new Post; implement post model and postdao to save
		Integer userId = (Integer) request.getSession().getAttribute(AbstractController.userSessionKey);
		User author = userDao.findByUid(userId);
		Post post = new Post(title, body, author);
		postDao.save(post);
		model.addAttribute("post",post);
		return "redirect:"+ post.getAuthor().getUsername() + "/" + post.getUid(); // TODO - this redirect should go to the new post's page
	}
	//curly braces represent the dynamic urls or request handlers here
		//handles request like /blog/chris/5
	@RequestMapping(value = "/blog/{username}/{uid}", method = RequestMethod.GET)
	public String singlePost(@PathVariable String username, @PathVariable int uid, Model model) {

		// TODO - implement singlePost

		//get parameters are already above

		//get the given post
		Post post= postDao.findOne(uid);
		//Post post = postDao.findByUid(uid);
		
		//past the given post into the template
		model.addAttribute("post", post);
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
