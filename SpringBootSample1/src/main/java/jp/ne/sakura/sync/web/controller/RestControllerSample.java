package jp.ne.sakura.sync.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestControllerSample {

	@RequestMapping("/")
	public String index(){
		return "Call RestController.index";
	}

}
