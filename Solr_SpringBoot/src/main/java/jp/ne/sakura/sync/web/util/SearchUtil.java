/**
 *
 */
package jp.ne.sakura.sync.web.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.common.StringUtils;

/**
 * @author $Author$
 *
 */
public class SearchUtil {

  public static final String ENCODING = "UTF-8";

  private static HashMap<String, String[][]> facetQueries;

  private static Map<String, String> facetLabels;

  private static String[] summaryHighlightFields = { "summary",
      "intended_reader", "from_author", "toc" };

  private static String titleHighlightField = "title";

  private static String[] fields = { "url", "title", "price", "author",
      "pages", "isbn" };

  static {
    facetQueries = new HashMap<String, String[][]>();
    String[][] yearsArr = { { "pub_date:[20090101 TO *]", "2009年以降" },

    { "pub_date:[20080101 TO 20081231]", "2008年" },
        { "pub_date:[20070101 TO 20071231]", "2007年" },
        { "pub_date:[20060101 TO 20061231]", "2006年" },
        { "pub_date:[20050101 TO 20051231]", "2005年" },
        { "pub_date:[* TO 20041231]", "2004年以前" } };

    facetQueries.put("発売年", yearsArr);

    facetLabels = new HashMap<String, String>();
    facetLabels.put("genre", "ジャンル");
    facetLabels.put("author", "著者");

  }

  /**
   * @return summaryHighlightFields
   */
  public static String[] getSummaryHighlightFields() {
    return summaryHighlightFields;
  }

  /**
   * @return titleHighlightField
   */
  public static String getTitleHighlightField() {
    return titleHighlightField;
  }

  /**
   * @return fields
   */
  public static String[] getFields() {
    return fields;
  }

  public static HashMap<String, String[][]> getFacetQueries() {
    return facetQueries;
  }

  public static Map<String, String> getFacetLabels() {
    return facetLabels;
  }

  public static String getFacetLabel(String fieldName) {
    String label = facetLabels.get(fieldName);
    if (label == null)
      label = fieldName;
    return label;
  }

  public static String getLinkStr(
    String query,
    String[] filterQueries,
    String filterValue,
    int start) {
    List<String> link = new ArrayList<String>();

    if (!StringUtils.isEmpty(query)) {
      link.add(getUrlEncodedParam("query", query));
    }

    if (filterQueries != null && filterQueries.length > 0) {
      for (String fq : filterQueries) {
        if (!StringUtils.isEmpty(fq)) {
          link.add(getUrlEncodedParam("fq", fq));
        }
      }
    }

    if (!StringUtils.isEmpty(filterValue)) {
      link.add(getUrlEncodedParam("fq", filterValue));
    }

    if (start > 0) {
      link.add("start=" + start);
    }

    String linkStr = "";
    for (int i = 0; i < link.size(); i++) {
      if (i == 0) {
        linkStr += "?";
      } else {
        linkStr += "&";
      }
      linkStr += link.get(i);
    }

    return linkStr;
  }

  private static String getUrlEncodedParam(String paramName, String value) {
    String tmp = "";
    try {
      tmp = paramName + "=" + URLEncoder.encode(value, ENCODING);
    } catch (UnsupportedEncodingException uee) {
    }
    return tmp;
  }

  public static int getFacetQueryTotalCount(
    String queryLabelName,
    Map<String, Integer> facetQueriesMap) {
    int total = 0;
    if (facetQueriesMap != null) {
      String[][] queriesArray = SearchUtil.getFacetQueries().get(queryLabelName);
      for (int i = 0; i < queriesArray.length; i++) {
        if (facetQueriesMap.containsKey(queriesArray[i][0])) {
          total += facetQueriesMap.get(queriesArray[i][0]).intValue();
        }
      }
    }
    return total;
  }
}
