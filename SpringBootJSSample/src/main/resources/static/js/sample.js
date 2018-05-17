function btnClick(){

	var inputText = document.getElementById("tex").value;
	var resultArea = document.getElementById("resultArea");

	if(inputText == ""){

		//未入力の場合はアラート表示
		alert("値を入力して下さい。");
		resultAreea.innerText = "";
		return;
	}

	var result = "入力された値は【" + inputText + "】です。";

	console.log("コンソールへ出力：" + result);
	resultArea.innerText = result;
}