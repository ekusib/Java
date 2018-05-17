package jp.ne.sakura.sync.web.service;



import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.util.NamedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.ne.sakura.sync.web.service2.SearchService;
import jp.ne.sakura.sync.web.util.SearchUtil;


@Service
public class SolrService {

	@Autowired
	public SearchService searchService;

	public Map searchService(String query, String[] fq, int searchStart) throws Exception{


		Map searchResult = new HashMap();

		QueryResponse rsp = null;
		try{
			rsp = searchService.searchSolr(query, fq, searchStart);
		}catch(NullPointerException npe){
		}

		long found = 0;
		long start = 0;
		int rows = 10;
		int qtime = 0;

		if(rsp != null){
		    qtime = rsp.getQTime();
			start = rsp.getResults().getStart();
			String rowsStr = (String)((NamedList)rsp.getResponseHeader().get("params")).get("rows");

			rows = Integer.parseInt(rowsStr);

			found = rsp.getResults().getNumFound();

		}

		List<Integer> numbers = new ArrayList<>();
		Integer foundInt = new Integer(String.valueOf(found));
		Integer startInt = new Integer(String.valueOf(start));
		Integer rowsInt = new Integer(rows);
		Integer qtimeInt = new Integer(qtime);
		numbers.add(foundInt);
		numbers.add(startInt);
		numbers.add(rowsInt);
		numbers.add(qtimeInt);


		List<List<String>> results = new ArrayList<>();

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

			List<String> result = new ArrayList<>();
			result.add((String)document.getFirstValue("url"));
			result.add((String)document.getFirstValue("title"));
			result.add(summary);
			result.add(authors);
			result.add(info);

			results.add(result);
		}
		List<Map<String, Map<String, String>>> facetFieldList = new ArrayList<>();
		List<String> facetLabelName = new ArrayList<>();
		for(FacetField facetField : rsp.getFacetFields()){

			if(facetField != null){

				facetLabelName.add(SearchUtil.getFacetLabel(facetField.getName()));

				List<Count> facetCountList = facetField.getValues();

				Map<String, String> countMap = new LinkedHashMap<>();
				for(Count count : facetCountList){
					countMap.put(count.getName(), String.valueOf(count.getCount()));
				}
				Map<String, Map<String, String>> facetFieldMap = new LinkedHashMap<>();
				facetFieldMap.put(facetField.getName(), countMap);
				facetFieldList.add(facetFieldMap);
			}
	    }


		List<Map<String, Map<String, String>>> facetQueryList = new ArrayList<>();
		List<String> facetQueryName = new ArrayList<>();
	    for(String queryLabelName : SearchUtil.getFacetQueries().keySet()){

	      if(SearchUtil.getFacetQueryTotalCount(queryLabelName, rsp.getFacetQuery()) > 0){

	    	facetQueryName.add(queryLabelName);

			String[][] queriesArray = SearchUtil.getFacetQueries().get(queryLabelName);
			Map<String, Integer> facetQueriesMap = rsp.getFacetQuery();
			if(facetQueriesMap != null){
			  for(int i=0;i<queriesArray.length;i++){
			    if(facetQueriesMap.containsKey(queriesArray[i][0])){
			      int count = facetQueriesMap.get(queriesArray[i][0]).intValue();
			      if(count > 0){
			    	  Map<String, String> countMap = new LinkedHashMap<>();
			    	  countMap.put(queriesArray[i][1], String.valueOf(count));
			    	  Map<String, Map<String, String>> facetQueryMap = new LinkedHashMap<>();
			    	  facetQueryMap.put(queriesArray[i][0], countMap);
			    	  facetQueryList.add(facetQueryMap);
			      }
			    }
			  }
			}
		  }
	    }


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
		List pagenation = new ArrayList();
		pagenation.add(pages);
		pagenation.add(pcnt);
		pagenation.add(cpag);

		List<List<String>> facetNames = new ArrayList<>();
		facetNames.add(facetLabelName);
		facetNames.add(facetQueryName);

		searchResult.put("results", results);
		searchResult.put("numbers", numbers);
		searchResult.put("facetFieldList", facetFieldList);
		searchResult.put("facetQueryList", facetQueryList);
		searchResult.put("pagenation", pagenation);
		searchResult.put("facetNames", facetNames);

		return searchResult;
	}

}
