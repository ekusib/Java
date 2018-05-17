package jp.ne.sakura.sync.web.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ParameterSampleController {

	@RequestMapping("/{num}")
	public String index(@PathVariable int num){

		// 消費税率
		double taxRate = 1.8;

		return "税込み価格=" + (int)(num * taxRate) + "円";
	}

}
