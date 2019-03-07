<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${cp }/js/jquery-3.3.1.min.js"></script>
<script>
	$(document).ready(function() {
		console.log("ajaxView.jsp");
	//JSon 데이터 요청
	$("#jsonReqBtn").on("click", function() {
// 		JsonView();
// 		responseBody();
		requestBody();
	});
});
// 	function JsonView() {
// 		$.ajax({
// 			url : "${cp}/ajax/jsonView",
// 			method : "post",
// 			success : function(data) {
			
// 		      var text = "";
// 		      for(var i=0; i<data.rangerList.length; i++){
// 		         text += "<tr>";
// 		         text += "   <td>"+data.rangerList[i]+"</td>";
// 		         text += "</tr>";
		      
// 		      }
// 		      $("#JsonRecvTbody").html(text);
// 		      console.log(text);
// 		   }
// 		});		
// 	}
	function requestBody() {
		var data = {userId : "brown", userNm : "브라운"};
		$.ajax({
			url : "${cp}/ajax/requestBody",
			method : "post",
// 			data : "userId=brown&userNm=브라운)",
// 			data : $("#frm").serialize(), // 보낼때 폼태그 안에있는 문자열을 형성 해준다. 
			data : JSON.stringify(data),
			dataType : "json",//server에게 희망하는 리턴타입
			contentType :"application/json; charset=utf-8",
			success : function(data) {
				$("#JsonRecvTbody").html("<tr><td>"+data.userId +"</td></tr>");
// 			console.log("ready")
// 		      var text = "";
// 		      for(var i=0; i<data.length; i++){
// 		         text += "<tr>";
// 		         text += "   <td>"+data[i]+"</td>";
// 		         text += "</tr>";
		      
// 		      }
// 		      $("#JsonRecvTbody").html(text);
		   }
		});		
	}

	function responseBody() {
		$.ajax({
			url : "${cp}/ajax/responseBody",
			method : "post",
			dataType : "json",//server에게 희망하는 리턴타입
			success : function(data) {
			console.log("ready")
		      var text = "";
		      for(var i=0; i<data.length; i++){
		         text += "<tr>";
		         text += "   <td>"+data[i]+"</td>";
		         text += "</tr>";
		      
		      }
		      $("#JsonRecvTbody").html(text);
		   }
		});		
	}

</script>
</head>
<body>
<form id="frm" >
	<input type="text" name="userId" value="brown">
	<input type="text" name="userNm" value="브라운">
</form>
	<h2>ajaxView</h2>
	<h3>json 수신</h3>
	<div>
		<button id="jsonReqBtn">jsonData 요청</button>
		<div id="jsonRecv"></div>
		<table>
			<thead>
				<tr>
					<th>이름</th>
				</tr>
			</thead>
			<tbody id="JsonRecvTbody">
				<tr>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>