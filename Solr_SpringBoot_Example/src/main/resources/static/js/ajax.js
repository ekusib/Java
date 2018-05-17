$(function(){



	/** キーワード検索されたとき */
	$('.querySearchBtn').unbind('click').bind('click', function(event){
        // クリック時に発生するイベントをキャンセル
        event.preventDefault();

        var url = "search";
        var query = $(".qTxt").val();
        var data = {

            "query" : query,
            "fq" : null,
            "start" : 0,
        }

        ajax(url,data);

	});

	/** ファセットフィールドが押されたとき */
	$(document).on("click",".facetField", function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);


	});

	/** ファセットクエリが押されたとき */
	$(document).on("click",".facetQuery", function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);

	});

	/** 先頭ページへが押されたとき */
	$(document).on("click", ".sentou",function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);


	});

	/** 前へが押されたとき */
	$(document).on("click", ".mae",function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);


	});

	/** ページ番号が押されたとき */
	$(document).on("click", ".pagination",function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);

	});

	/** 次へが押されたとき */
	$(document).on("click", ".tugi",function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);


	});

	/** 末尾ページへが押されたとき */
	$(document).on("click", ".matubi",function(event){

		// クリック時に発生するイベントをキャンセル
		event.preventDefault();

		var query = $(this).data("query");
		var fq = $(this).data("fq");
		var start =  $(this).data("start");

		facetPaging(query,fq,start);


	});

	/**ファセット、ページングが押されたときの処理*/
	function facetPaging(query,fq,start){

		var url = "facet";

		if(fq == undefined){

			fq = null;

		}

		if(query == undefined){

			query = "";

		}

		var data = {

            "query" : query,
            "fq" : fq,
            "start" : start,
        }

        ajax(url,data);

	}

	/**AjaxにデータをセットしてControllerに値を送り、返却してもらう処理**/
	function ajax(url,data){

		$.ajax({
            type : "POST",
            url : url,
			data: data,
            dataType : "json",

            success : function(jsonData){

            	var query = jsonData.query;
                var fq = jsonData.fq;
                var data = jsonData.data;
                var results = jsonData.results;
                var facet_fields = jsonData.facet_fields;
                var facet_queries = jsonData.facet_queries;
                var pagination = jsonData.pagination;

                var found = data.found;
                var start = data.start;
                var rows = data.rows;
                var qtime = data.qtime;
                var pages = pagination.pages;
                var pcnt = pagination.pcnt;
                var cpag = pagination.cpag;



                if(query != ""){

                	var title = query+"の検索結果";
                	document.title = title;

                }


        		$("#result_header").empty();

        		if(query != ""){

        		$("#result_header").append(`<b>${query}</b> の検索結果&nbsp;&nbsp;&nbsp;`);

        		}

                if(found > 0){


                	$("#result_header").append(`

                	<b>${found}</b> 件中
                	<b>${start+1}</b> -

                	`);

                	if((rows+start) > found){

                		$("#result_header").append(`

                		<b>${found}</b> 件目

                		`);

                	}else{

                		$("#result_header").append(`

                		<b>${rows+start}</b> 件目

                		`);

                	}
                }else{

                	$("#result_header").append(`

                    	<b>0</b> 件

                    `);
                }

                $("#result_header").append(`

                	<span>( ${qtime} 秒 )</span>
                `);


                if( found == 0){

                	$("#content").empty();
                	$("#page").empty();
                	$("#content").append(`

                		<div> <b>${query}</b> は見つかりませんでした。他の検索語で試してください。</div>

                	`);


                }else{

                $("#content").empty();
                $("#content").append(`

                <div id="result" class="right"></div>
                <div id="facet" class="left"></div>
                <div style="CLEAR: both"></div>

                        	`);
                $.each(results, function(i,result){

                	var url = result.url;
                	var title = result.title;
                	var summary = result.summary;
                	var author = result.author;
                	var info = result.info;



                	$("#result").append(`


                		<h2>
                			<a href="${url}" id="resultTitle">[${title}]</a>
            			</h2>
    					<div class="articles" id="resultSummary">${summary}</div>
    					<br/>
    					<div class="articles" id="resultAuthor">${author}</div>
						<div class="articles" id="resultInfo">${info}</div>
						<hr/>

					`);
                })

                $.each(facet_fields, function(i,facetField){

                	var facetLabelName = facetField.facetLabelName;

                	$("#facet").append(`

                		<h2 class="facetLabelName">${facetLabelName}</h2>

                	`);

                	$.each(facetField.facetFieldArray, function(i,facetFieldArray){

                		var nameStr = facetFieldArray.nameStr;
                		var countLong = facetFieldArray.countLong;
                		var label;

                		if(facetLabelName =="著者"){
                			lavel = "author";
                		}else{
                			lavel = "genre";
                		}

                		$("#facet").append(`

                		<a href="#" data-query="${query}" data-fq="${lavel}:${nameStr}" data-start="${start}" class="facetField">${nameStr}(${countLong})</a><br />

                		`)
                	})
                })

                $.each(facet_queries, function(i,facetQuery){

                	var queryLabelName = facetQuery.queryLabelName;

                	$("#facet").append(`

                		<h2>${queryLabelName}</h2>

                	`);

                	$.each(facetQuery.facetCountLink, function(i,queryFieldArray){

                		var queryStr = queryFieldArray.queryStr;
                		var countInt = queryFieldArray.countInt;
                		var pubStr = queryFieldArray.pubStr;

                		$("#facet").append(`

                		<a href="#" data-query="${query}" data-fq="${pubStr}" data-start="${start}" class="facetQuery">${queryStr}(${countInt})</a><br />

                		`)
                	})
                })


                $("#page").empty();

                if(pcnt > 1){

                	$("#page").append(`<strong>Page</strong>`);

                	if(cpag > 1){

                		var maeStart = (cpag-2) * rows;

                		$("#page").append(`

                		<a href="#" data-query="${query}" data-fq="${fq}" data-start=0 class="sentou">|&lt;&lt;先頭ページへ</a>&nbsp;&nbsp;&nbsp;
                		<a href="#" data-query="${query}" data-fq="${fq}" data-start="${maeStart}" class="mae">&lt;前へ</a>&nbsp;&nbsp;&nbsp;

                		`);
                	}

                	$("#page").append(`&nbsp;`);

                	$.each(pages, function(i,page){

                		if(cpag == page){

                			$("#page").append(`<span>${page}</span>&nbsp;&nbsp;&nbsp;`);

                		}else{

                			var pageStart = (page-1) * rows;
                			$("#page").append(`<a href="#" data-query="${query}" data-fq="${fq}" data-start="${pageStart}" class="pagination">${page}</a>&nbsp;&nbsp;&nbsp;`);

                		}

                	})

                	$("#page").append(`&nbsp;`);

                	if(cpag < pcnt){

                		var nextStart = cpag * rows;
                		var matubiStart = (pcnt-1) * rows;

                		$("#page").append(`<a href="#" data-query="${query}" data-fq="${fq}" data-start="${nextStart}" class="tugi">次へ&gt;</a>&nbsp;&nbsp;&nbsp;`);
                		$("#page").append(`<a href="#" data-query="${query}" data-fq="${fq}" data-start="${matubiStart}" class="matubi">末尾ページへ&gt;&gt;|</a>&nbsp;&nbsp;&nbsp;`);
                	}
                }
              }

            },
            error : function (XMLHttpRequest, textStatus, errorThrown) {
                console.log(XMLHttpRequest); // XMLHttpRequestオブジェクト
                console.log(textStatus); // status は、リクエスト結果を表す文字列
                console.log(errorThrown); // errorThrown は、例外オブジェクト
            },
        });
	}


})