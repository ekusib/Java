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

	@RequestMapping("/delete/{name}/{mail}/{positionNo}")
	public String delete(@PathVariable String name, @PathVariable String mail, @PathVariable int positionNo, Model model){
		dBOperationService.delete(name, mail, positionNo);
			List<Employee> empList = dBOperationService.getEmployeeforRange(0, 100);
		model.addAttribute("emp", empList);
		return "index";
	}


}
