package jp.ne.sakura.sync.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JavaScriptController {

	@RequestMapping("/")
	public String index(){
		return "index";
	}
}
