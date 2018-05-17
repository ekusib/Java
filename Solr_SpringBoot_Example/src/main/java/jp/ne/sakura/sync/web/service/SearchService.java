/**
 *
 */
package jp.ne.sakura.sync.web.service;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.FacetParams;
import org.apache.solr.common.params.HighlightParams;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import jp.ne.sakura.sync.web.util.SearchUtil;

/**
 * @author $Author$
 *
 */
@Service
public class SearchService {

	/**
	 * Solrを全件検索して表示情報を取得
	 */

	SolrServer solrServer = null;

	public QueryResponse searchSolr(String searchWord, String[] filter, int searchStart)
			throws ServletException, IOException {

		//接続するSolrの情報を指定
		// solrServer = new HttpSolrServer("http://192.168.1.10:8983/solr/book");
		solrServer = new HttpSolrServer("http://120.51.37.244:8983/solr/solrbook");

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
		}else {
			searchWord = "title:" + searchWord + ", summary:" + searchWord + ", author:" + searchWord;
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