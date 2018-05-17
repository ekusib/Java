package jp.ne.sakura.sync.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ExpressionSample {

	@RequestMapping("/{id}")
	public String index(@PathVariable int id, Model model){

		model.addAttribute("id", id);
		model.addAttribute("check", id % 2 == 0);
		model.addAttribute("trueVal", "Even number!");
		model.addAttribute("falseVal", "odd number!");

		// Switch文判定用
		int m = Math.abs(id);
		m = m == 0 ? 12 : m;
		model.addAttribute("month", m);
		model.addAttribute("switchCheck", Math.floor(m /3));

		return "index";
	}
}
