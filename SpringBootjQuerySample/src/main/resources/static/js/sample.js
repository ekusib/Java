$(function(){
	// クリックイベント設定
	$("#btn").click(
	    function(){
		btnClick();
	    }
	);

	// クリック時に呼ばれる関数本体
	function btnClick(){

	    var inputText = $("#tex").val();
	    var resultArea = $("#resultArea");

	    if(inputText == ""){

	        // 未入力の場合はアラート表示
	        alert("値を入力してください。");
	        resultArea.html("");
	        return;
	    }

	    var result = "入力された値は【" + inputText + "】です。";

	    console.log("コンソールへ出力：" + result);
	    resultArea.html(result);
	}
});
