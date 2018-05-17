package jp.ne.sakura.sync.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.ne.sakura.sync.web.entity.Employee;
import jp.ne.sakura.sync.web.service.DBOperationService;

@Controller
public class DBController {

	@Autowired
	public DBOperationService dBOperationService;

	@RequestMapping("/customSearch/{from}/{to}/")
	public String customSearch(@PathVariable int from, @PathVariable int to, Model model){
	List<Employee> empList = dBOperationService.getEmpWithPositionName(from, to);
	model.addAttribute("emp", empList);
		return "custom";
	}
}
