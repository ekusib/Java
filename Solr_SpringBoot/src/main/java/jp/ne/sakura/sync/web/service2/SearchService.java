/**
 *
 */
package jp.ne.sakura.sync.web.service2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.StringUtils;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.HighlightParams;
import org.springframework.beans.factory.annotation.Autowired;

import jp.ne.sakura.sync.web.util.SearchUtil;

/**
 * @author $Author$
 *
 */
public class SearchService {

	@Autowired
	private HttpSession session;

	SolrServer solrServer = null;

	public QueryResponse searchSolr(String searchWord, String[] filter, int searchStart) throws ServletException, IOException {

		try{
			String solrServerUrl = (String)session.getAttribute("solrServerUrl");
			solrServer = new HttpSolrServer(solrServerUrl);
		}catch(NullPointerException npe){
		}

		// 検索条件生成
		SolrQuery query = createSolrQuery(searchWord, filter, searchStart);

		// 結果受け取り
		QueryResponse rsp = null;
		try {
		  rsp = solrServer.query(query);
		} catch (SolrServerException sse) {
		  sse.printStackTrace();
		}

		return rsp;
	}


	private String getQueryString(String searchWord) {

		if (StringUtils.isEmpty(searchWord)) {
			searchWord = "*:*";
	    }

	    return searchWord;
	  }


	private String getPerFieldParameterName(String field, String paramName) {

	    return "f." + field + "." + paramName;
	}


	private SolrQuery createSolrQuery(String searchWord, String[] filter, int searchStart) {
	    SolrQuery query = new SolrQuery();
	    query.setRows(10);
	    // fl（取得フィールドの指定）
		query.setFields(SearchUtil.getFields());

		// ハイライト
		query.setHighlight(true);
		query.addHighlightField(SearchUtil.getTitleHighlightField());
		for (String field : SearchUtil.getSummaryHighlightFields()) {
		  query.addHighlightField(field);
		}
		query.setHighlightSnippets(1);
		query.setHighlightSimplePre("<b style=\"background:aquamarine\">");
		query.setHighlightSimplePost("</b>");
		query.set(HighlightParams.USE_PHRASE_HIGHLIGHTER, true);
		query.set(getPerFieldParameterName("title", HighlightParams.ALTERNATE_FIELD), "title");
		query.set(getPerFieldParameterName("summary", HighlightParams.ALTERNATE_FIELD), "summary");
		query.set(getPerFieldParameterName("summary", HighlightParams.ALTERNATE_FIELD_LENGTH), 100);

		// ファセット
		query.setFacet(true);
		query.setFacetLimit(20);
		query.setFacetMinCount(1);
		query.setFacetSort(FacetParams.FACET_SORT_COUNT);
		// ファセットフィールド
		for (String field : SearchUtil.getFacetLabels().keySet()) {
		  query.addFacetField(field);
		}
		for (String category : SearchUtil.getFacetQueries().keySet()) {
		  for (int i = 0; i < SearchUtil.getFacetQueries().get(category).length; i++) {
		    query.addFacetQuery(SearchUtil.getFacetQueries().get(category)[i][0]);
		  }
		}

		// 検索語の設定
		query.setQuery(getQueryString(searchWord));
		// start（取得開始位置）
		int start = searchStart;
		query.setStart(start);

		// fq（絞り込み条件の取得）
		String[] filterQueries = filter;
		if (filterQueries != null) {
		  query.addFilterQuery(filterQueries);
		}
	    return query;
	}
}