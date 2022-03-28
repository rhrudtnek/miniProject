package guestbook.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import guestbook.bean.GuestbookDTO;
import guestbook.service.GuestbookService;

@Controller
@RequestMapping(value = "guestbook")
public class GuestbookController {
	@Autowired
	private GuestbookService guestbookService;
	
	@GetMapping(value = "guestbookWriteForm")
	public String guestbookWriteForm(Model model) {
		model.addAttribute("display", "/guestbook/guestbookWriteForm.jsp");
		return "/index";
	}
	
	@PostMapping(value = "guestbookWrite")
	@ResponseBody
	public void guestbookWrite(@ModelAttribute GuestbookDTO guestbookDTO) {
		guestbookService.guestbookWrite(guestbookDTO);
	}
	
	@GetMapping(value = "guestbookList")
	public String guestbookList(@RequestParam(required = false, defaultValue = "1") String pg, Model model) {
		model.addAttribute("pg", pg);
		model.addAttribute("display", "/guestbook/guestbookList.jsp");
		return "/index";
	}
	
	@PostMapping(value = "getGuestbookList")
	@ResponseBody
	public Map<String, Object> getGuestbookList(@RequestParam String pg){
		return guestbookService.getGuestbookList(pg);
	}

}
