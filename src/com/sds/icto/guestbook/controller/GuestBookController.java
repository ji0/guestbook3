package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sds.icto.guestbook.dao.GuestBookDAO;
import com.sds.icto.guestbook.vo.GuestBookVO;


@Controller
public class GuestBookController {

	@Autowired
	GuestBookDAO guestBookDao;

	@RequestMapping("/index")
	public String index(Model model) {

		List<GuestBookVO> list = guestBookDao.fetchList();
		model.addAttribute("list", list);

		return "/views/index.jsp";
	}

	@RequestMapping("/deleteform")
	public String form() {
		return "views/deleteform.jsp";

	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(
			@RequestParam String name,
			@RequestParam String password, 
			@RequestParam String message) {
		
		GuestBookVO vo = new GuestBookVO();
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		guestBookDao.insert(vo);

		return "redirect:/index";
	}
	
	
	@RequestMapping("/delete")
	public String delete() {
		

		
		/*System.out.println(no);
		
		Long lNo = Long.parseLong(no);

		guestBookDao.delete(lNo, password);
		*/
		
		return "redirect:/index";

	}


}
