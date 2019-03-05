<%@page import="kr.or.ddit.prod.model.ProdVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">

<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css"
   rel="stylesheet">

</head>
<body>
<%@ include file="/WEB-INF/views/module/header.jsp" %>
	<%@ include file="/WEB-INF/views/module/footer.jsp" %>
	
	<!-- 정적 include -->
	<br>



	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">전체 상품 정보조회</h1>


		<!--          </div> -->
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>ProdID</th>
						<th>ProdName</th>
						<th>ProdCoast</th>
						<th>등록일시</th>
					</tr>
				</thead>
				<tbody>
					<%
				
					List<ProdVo> prodList = (List<ProdVo>)request.getAttribute("selectProd");
						for(int i =0; i<prodList.size(); i++){   
					            
					                out.print("<tr>");
					                out.print("<td></td>");
					              	out.print("<td>"+prodList.get(i).getProd_id()+"</td>");
					                out.print("<td>"+prodList.get(i).getProd_name()+"</td>");
					                out.print("<td>"+prodList.get(i).getProd_buyer()+"</td>");
					              	out.print("<td>"+prodList.get(i).getProd_cost()+"</td>");
					                out.print("</tr>");
					                
					              }
					%>

				</tbody>
			</table>
		</div>
		<!-- Bootstrap core JavaScript
    ================================================== -->
		<!-- Placed at the end of the document so the pages load faster -->
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
		<%-- 순서 중요 --%>
		<script
			src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
		<script>

		</script>
</body>
</html>
<%-- localhost/module/main.jsp --%>