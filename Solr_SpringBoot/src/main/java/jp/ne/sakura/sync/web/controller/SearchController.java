package jp.ne.sakura.sync.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.ArrayUtils;

import jp.ne.sakura.sync.web.service.SolrService;

@Controller
public class SearchController {

	@Autowired
	private SolrService solrService;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) throws Exception{

		String[] fq = null;
		Map searchResult = new HashMap();

		searchResult = solrService.searchService("", fq, 0);

		List<Integer> numbers = (List<Integer>)searchResult.get("numbers");
		List pagenation = (List)searchResult.get("pagenation");
		List<Map<String, Map<String, String>>> facetQueryList = (List<Map<String, Map<String, String>>>)searchResult.get("facetQueryList");
		List<Map<String, Map<String, String>>> facetFieldList = (List<Map<String, Map<String, String>>>)searchResult.get("facetFieldList");

		model.addAttribute("query", "");
		model.addAttribute("fq", fq);
		model.addAttribute("numbers", numbers);
		model.addAttribute("facetFieldList", facetFieldList);
		model.addAttribute("facetQueryList", facetQueryList);
		model.addAttribute("pagenation", pagenation);
		model.addAttribute("results", searchResult.get("results"));

		return "index";

	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(@RequestParam("query") String query, Model model) throws Exception{

		String[] fq = null;
		Map searchResult = new HashMap();

		searchResult = solrService.searchService(query, fq, 0);

		List<Integer> numbers = (List<Integer>)searchResult.get("numbers");
		List<Map<String, Map<String, String>>> facetFieldList = (List<Map<String, Map<String, String>>>)searchResult.get("facetFieldList");
		List<Map<String, Map<String, String>>> facetQueryList = (List<Map<String, Map<String, String>>>)searchResult.get("facetQueryList");
		List pagenation = (List)searchResult.get("pagenation");

		model.addAttribute("query", query);
		model.addAttribute("fq", fq);
		model.addAttribute("numbers", numbers);
		model.addAttribute("facetFieldList", facetFieldList);
		model.addAttribute("facetQueryList", facetQueryList);
		model.addAttribute("pagenation", pagenation);
		model.addAttribute("results", searchResult.get("results"));

		return "index";
	}

	@RequestMapping(value = "/solr", method = RequestMethod.GET)
	public String page(@RequestParam("query") String query, @RequestParam("fq") String[] fq, @RequestParam("start") int searchStart, Model model) throws Exception{

		Map searchResult = new HashMap();

		searchResult = solrService.searchService(query, fq, searchStart);

		if(ArrayUtils.isEmpty(fq)){
			fq = null;
		}

		List<Integer> numbers = (List<Integer>)searchResult.get("numbers");
		List<Map<String, Map<String, String>>> facetFieldList = (List<Map<String, Map<String, String>>>)searchResult.get("facetFieldList");
		List<Map<String, Map<String, String>>> facetQueryList = (List<Map<String, Map<String, String>>>)searchResult.get("facetQueryList");
		List pagenation = (List)searchResult.get("pagenation");

		model.addAttribute("query", query);
		model.addAttribute("fq", fq);
		model.addAttribute("numbers", numbers);
		model.addAttribute("facetFieldList", facetFieldList);
		model.addAttribute("facetQueryList", facetQueryList);
		model.addAttribute("pagenation", pagenation);
		model.addAttribute("results", searchResult.get("results"));

		return "index";
	}


}


