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

import org.thymeleaf.util.StringUtils;



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

  private static String[] fields = { "id", "title", "price", "author",
      "pages", "isbn" };

  static {
    facetQueries = new HashMap<String, String[][]>();
    String[][] yearsArr = { { "pub_time:[2009-01-10T00:00:00Z TO *]", "2009年以降" },
            { "pub_time:[2008-01-10T00:00:00Z TO 2008-12-31T00:00:00Z]", "2008年" },
            { "pub_time:[2007-01-10T00:00:00Z TO 2007-12-31T00:00:00Z]", "2007年" },
            { "pub_time:[2006-01-10T00:00:00Z TO 2006-12-31T00:00:00Z]", "2006年" },
            { "pub_time:[2005-01-10T00:00:00Z TO 2005-12-31T00:00:00Z]", "2005年" },
            { "pub_time:[* TO 2004-12-31T00:00:00Z]", "2004年以前" } };
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
