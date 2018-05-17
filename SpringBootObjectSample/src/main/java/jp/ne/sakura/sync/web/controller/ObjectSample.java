package jp.ne.sakura.sync.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ne.sakura.sync.web.model.DataModel;

@Controller
public class ObjectSample {

	@RequestMapping("/")
	public String index(Model model){

		model.addAttribute("msg", "current data.");
		DataModel obj = new DataModel(100, "shimizu", "t.shimizu@sync-web.jp");
		model.addAttribute("obj", obj);

		return "index";
	}
}
