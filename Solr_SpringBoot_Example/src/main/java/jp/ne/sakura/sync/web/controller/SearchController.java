package jp.ne.sakura.sync.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.ArrayUtils;

import jp.ne.sakura.sync.web.model.SolrSpringBootModel;
import jp.ne.sakura.sync.web.service.ExtractService;

@Controller
public class SearchController {

	@Autowired ExtractService es;
	/***************************************初期表示用***************************************/

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) throws Exception{


		String query = "";
		String[] fq = null;
		int searchStart = 0;

		SolrSpringBootModel solrSpringBootModel= es.extract(query,fq,searchStart);

		model.addAttribute("solrSpringBootModel", solrSpringBootModel);

		return "index";
		}

	/***************************************検索用***************************************/

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	@ResponseBody
	public SolrSpringBootModel search(@RequestParam("query") String query, @RequestParam("fq") String[] fq, @RequestParam("start") int  searchStart, Model model) throws Exception{

		SolrSpringBootModel solrSpringBootModel= es.extract(query,fq,searchStart);

		return solrSpringBootModel;
	}

	/***************************************ファセット用***************************************/

	@RequestMapping(value = "/facet", method = RequestMethod.POST)
	@ResponseBody
	public SolrSpringBootModel page(@RequestParam("query") String query, @RequestParam("fq") String[] fq, @RequestParam("start") int searchStart, Model model) throws Exception{

		if(ArrayUtils.isEmpty(fq)){
			fq = null;
		}

		SolrSpringBootModel solrSpringBootModel= es.extract(query,fq,searchStart);

		return solrSpringBootModel;

	}

}
