package jp.ne.sakura.sync.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ControllerSample {


	@RequestMapping("/")
	public ModelAndView index(ModelAndView mav){

		//テンプレートへ渡す値を設定
		mav.addObject("msg","ModelAndViewから値を渡しました。");

		//ビュー名を設定
		mav.setViewName("index");

		//ModelAndViewオブジェクトを返却
		return mav;
	}
}
