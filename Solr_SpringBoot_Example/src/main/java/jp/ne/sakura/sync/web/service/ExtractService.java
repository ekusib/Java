package jp.ne.sakura.sync.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ne.sakura.sync.web.model.DataModel;
import jp.ne.sakura.sync.web.model.FacetFieldArray;
import jp.ne.sakura.sync.web.model.FacetFieldModel;
import jp.ne.sakura.sync.web.model.FacetQueryArray;
import jp.ne.sakura.sync.web.model.FacetQueryModel;
import jp.ne.sakura.sync.web.model.PageModel;
import jp.ne.sakura.sync.web.model.RightModel;
import jp.ne.sakura.sync.web.model.SolrSpringBootModel;
import jp.ne.sakura.sync.web.util.SearchUtil;

@Service
public class ExtractService {

	@Autowired
	public SearchService searchService;

	public SolrSpringBootModel extract(String query, String[] fq, int searchStart) throws ServletException, IOException{

		QueryResponse rsp = null;

		try{
			rsp = searchService.searchSolr(query, fq, searchStart);
		}catch(NullPointerException npe){
			npe.printStackTrace();
		}

	/*************ヘッダー部分に使う値を抽出し、モデルにセット*************/

	long found = 0;
	long start = 0;
	int rows = 10;
	int qtime = 0;

	if(rsp != null){
	    qtime = rsp.getQTime();
		start = rsp.getResults().getStart();
		String rowsStr = (String)((NamedList)rsp.getResponseHeader().get("params")).get("rows");
		try{
			rows = Integer.parseInt(rowsStr);
		}catch(NumberFormatException nfe){

		}
		found = rsp.getResults().getNumFound();
	}

	DataModel data = new DataModel(found,start,rows,qtime);



	/*************画面右側部分に必要な値を抽出し、モデルにセット*************/

	List<RightModel> results = new ArrayList<>();

	for(SolrDocument document : rsp.getResults()){

		Map<String, List<String>> highlighting = rsp.getHighlighting().get(document.getFirstValue("url"));
		String summary = "";
		String authors = "著者：";
		boolean first = true;
		if(document.getFieldValues("author") != null){
		  for(Object author : document.getFieldValues("author")){
		    if(!first){
		      authors += ", ";
		    }else{
		      first = false;
		    }
		    authors += author.toString();
		  }
		}

		first = true;
		for(String field : SearchUtil.getSummaryHighlightFields()){

		  if(highlighting != null && highlighting.get(field) != null){
		    for(String text : highlighting.get(field)){
		      if(!first){
		        summary += "...";
		      }else{
		        first = false;
		      }
		      summary += text;
		    }
		  }
		}
		String info = "";
		if(document.getFirstValue("price") != null){
		  info += "定価："+document.getFirstValue("price")+"円";
		}
		if(document.getFirstValue("pages") != null){
		  if(info.length() > 0){
		    info += " | ";
		  }
		  info += document.getFirstValue("pages")+"ページ";
		}
		if(document.getFirstValue("isbn") != null){
		  if(info.length() > 0){
		    info += " | ";
		  }
		  info += document.getFirstValue("isbn");
		}

		String url = (String)document.getFirstValue("id");
		String title =(String)document.getFirstValue("title");

		RightModel right = new RightModel(url,title,summary,authors,info);

		results.add(right);
	}


	/*************ファセットフィールドに必要な値を抽出し、モデルにセット*************/

	List<FacetFieldModel> facet_fields = new ArrayList<>();

	for(FacetField facetField : rsp.getFacetFields()){

		if(facetField != null){

			List<Count> facetCountList = facetField.getValues();

			String facetLabelName = SearchUtil.getFacetLabel(facetField.getName());

			List<FacetFieldArray> facet_fieldArray = new ArrayList<>();

			for(Count count : facetCountList){
				String facetName = facetField.getName();
				String nameStr = count.getName();
				long countLong = count.getCount();


				FacetFieldArray facet_field = new FacetFieldArray(facetName, nameStr, countLong);

				facet_fieldArray.add(facet_field);

			}

			FacetFieldModel facetFieldModel = new FacetFieldModel(facetLabelName,facet_fieldArray);
			facet_fields.add(facetFieldModel);
		}
	}

			/*************ファセットクエリに必要な値を抽出し、モデルにセット*************/

	List<FacetQueryModel> facet_queries = new ArrayList<>();

	for(String queryLabelName : SearchUtil.getFacetQueries().keySet()){

		if(SearchUtil.getFacetQueryTotalCount(queryLabelName, rsp.getFacetQuery()) > 0){

			String[][] queriesArray = SearchUtil.getFacetQueries().get(queryLabelName);
			Map<String, Integer> facetQueriesMap = rsp.getFacetQuery();

			if(facetQueriesMap != null){

				List<FacetQueryArray> facetCountLink = new ArrayList();
				for(int i=0;i<queriesArray.length;i++){
					if(facetQueriesMap.containsKey(queriesArray[i][0])){

						int countInt = facetQueriesMap.get(queriesArray[i][0]).intValue();

						if(countInt > 0){
							String queryStr = queriesArray[i][1];
							String pubStr = queriesArray[i][0];
							FacetQueryArray facetQueryArray = new FacetQueryArray(countInt,queryStr,pubStr);
							facetCountLink.add(facetQueryArray);

						}
					}
				}
				FacetQueryModel facet_query = new FacetQueryModel(queryLabelName,facetCountLink,queriesArray);
				facet_queries.add(facet_query);
			}

		}

	}
    /*************ページング部分に必要な値を抽出し、モデルにセット*************/

	int wsiz = 10;
	int w1 = 5;
	int w2 = 5;

	int pcnt = (int)(found / rows + ( ( found % rows ) == 0 ? 0 : 1 ));
	int cpag = (int)(start / rows + 1);
	int wbgn = cpag - w1;
	int wend = cpag + w2;
	if( wbgn < 1 ){
	  wbgn = 1;
	  wend = wbgn + wsiz;
	  if( wend > pcnt + 1 ){
	    wend = pcnt + 1;
	  }
	}
	if( wend > pcnt + 1 ){
	  wend = pcnt + 1;
	  wbgn = wend - wsiz;
	  if( wbgn < 1 ){
	    wbgn = 1;
	  }
	}

	List<Integer> pages = new ArrayList<>();
	for(int i = wbgn; i < wend; i++){
		pages.add(i);
	}

	PageModel pagination = new PageModel(pages,pcnt,cpag);


	SolrSpringBootModel solrSpringBootModel = new SolrSpringBootModel();

	solrSpringBootModel.setQuery(query);
	solrSpringBootModel.setFq(fq);
	solrSpringBootModel.setData(data);
	solrSpringBootModel.setResults(results);
	solrSpringBootModel.setFacet_fields(facet_fields);
	solrSpringBootModel.setFacet_queries(facet_queries);
	solrSpringBootModel.setPagination(pagination);

	return solrSpringBootModel;

	}

}
