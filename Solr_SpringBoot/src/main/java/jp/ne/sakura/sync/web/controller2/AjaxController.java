package jp.ne.sakura.sync.web.controller2;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.ne.sakura.sync.web.model.SearchModel;
import jp.ne.sakura.sync.web.service.SolrService;

@Controller
public class AjaxController {

	@Autowired
	private SolrService solrService;

	@RequestMapping(value="/ajaxSearch",consumes=MediaType.APPLICATION_JSON_VALUE)

	@ResponseBody
	public Map search(@RequestBody SearchModel searchModel) throws Exception{

		return solrService.searchService(searchModel.getQuery(), searchModel.getFq(), searchModel.getStart());

	}

}
