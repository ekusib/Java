$(function(){

	// 初期ページを表示
	initialSearch();

	$("#initialSearch").on("click", function(){
		initialSearch();
	});

	$("#querySearchBtn").on("click", function(){
		$('html, body').animate({scrollTop:0}, 'fast');
		querySearch();
	});

	$(document).on("click", ".fq", function(){
		$('html, body').animate({scrollTop:0}, 'fast');
		fqSearch($(this).data("fq"));
	});

	$(document).on("click", ".start", function(){
		$('html, body').animate({scrollTop:0}, 'fast');
		startSearch($(this).data("start"));
	});


	function initialSearch(){

		var data = {
				"query" : "",
				"fq" : null,
				"start" : 0,
		}

		$("#searchQuery").data("searchQuery", "");
		$("#searchFq").data("searchFq", "");

		ajax(data);
	}

	function querySearch(){

		var query = $("#query").val();
		var data = {
				"query" : query,
				"fq" : null,
				"start" : 0,
		}

		$("#searchQuery").data("searchQuery", query);
		$("#searchFq").data("searchFq", "");

		ajax(data);
	}

	function fqSearch(fq){

		var searchQuery = $("#searchQuery").data("searchQuery");
		var searchFq = $("#searchFq").data("searchFq");
		var fqArray = new Array();
		$.each(searchFq, function(index, value){
			fqArray.push(value);
		});
		fqArray.push(fq);
		var data = {
				"query" : searchQuery,
				"fq" : fqArray,
				"start" : 0,
		}

		$("#searchQuery").data("searchQuery", searchQuery);
		$("#searchFq").data("searchFq", fqArray);

		ajax(data);
	}

	function startSearch(start){

		var searchQuery = $("#searchQuery").data("searchQuery");
		var searchFq = $("#searchFq").data("searchFq");
		var fqArray = new Array();
		$.each(searchFq, function(index, value){
			fqArray.push(value);
		});
		var data = {
				"query" : searchQuery,
				"fq" : fqArray,
				"start" : start,
		}

		$("#searchQuery").data("searchQuery", searchQuery);
		$("#searchFq").data("searchFq", fqArray);

		ajax(data);
	}

	function ajax(data){

		$.ajax({
			type : "post",
			url : "/ajaxSearch",
			data : JSON.stringify(data),
			contentType : 'application/json',
			dataType : "json",

			success : function(jsonData){

				var results = jsonData["results"];
				var numbers = jsonData["numbers"]
				var facetFieldList = jsonData["facetFieldList"];
				var facetQueryList = jsonData["facetQueryList"];
				var facetNames = jsonData["facetNames"];
				var pagenation = jsonData["pagenation"];

				var found = numbers[0];
				var start = numbers[1];
				var rows = numbers[2];
				var pages = pagenation[0];
				var pcnt = pagenation[1];
				var cpag = pagenation[2];


			}
		}
	}
}