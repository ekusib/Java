package jp.ne.sakura.sync.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlSample {

	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("msg", "message 1<hr />message 2<hr />message 3");
		return "index";
	}
}
