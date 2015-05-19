package com.sds.icto.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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

		return "index";
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
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	// 링크는 겟방식
	public String deleteForm(@RequestParam Long no) {
	
		return "deleteform";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(@RequestParam Long no, @RequestParam String password) {
		
		guestBookDao.delete(no, password);
				
		return "redirect:/index";

	}


}
