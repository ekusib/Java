package jp.ne.sakura.sync.web.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForEachSample {

	@RequestMapping("/")
	public String index(Model model){
		ArrayList<String[]> dataArray = new ArrayList<>();
		dataArray.add(new String[]{"ito", "ito@sync-web.jp", "090-9999-9999"});
		dataArray.add(new String[]{"tamagaki", "m.tamagaki@sync-web.jp", "080-8888-8888"});
		dataArray.add(new String[]{"shimizu", "t.shimizu@sync-web.jp", "070-7777-7777"});
		model.addAttribute("data", dataArray);
		return "index";
	}
}
