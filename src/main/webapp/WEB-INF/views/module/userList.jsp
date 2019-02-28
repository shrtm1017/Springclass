<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<!-- 정적 include -->
	<br>



	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">전체 사용자 정보조회</h1>

		<%-- <%--             <%=request.getAttribute("userList") %> --%>

		<!--          </div> -->
		<div class="table-responsive">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>#</th>
						<th>사용자아이디</th>
						<th>사용자 이름</th>
						<th>별명</th>
						<th>등록일시</th>
					</tr>
				</thead>
				<tbody>
				  <c:forEach items="${userpageList }" var ="user" varStatus="i">
                <%-- 향상된 for문은 begin이 없어서 i값을 구할 수 없음으로 varStatus를 이용해서 구한다 --%> 
                   <tr class='userTr' data-userid='${user.userId }'>
                      <td> ${i.index } </td>
                      <td> ${user.userId }</td>
                      <td> ${user.userNm }</td>
                      <td> --- </td>
                      <td> ${user.reg_dt_fmt }</td>
                   </tr>
               </c:forEach>

				</tbody>
			</table>
		</div>
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
			$(document).ready(function() {
				console.log("document reday");

				//사용시 TR 태그 클릭시 이벤트 핸들러
				$(".userTr").on("click", function() {
					console.log("userTr click");
					var userId = $(this).data("userid");
					 	
						// /user/
						
						//1. document
// 						document.location = "/user?userId=" + userId;
						//2. form
						$("#userId").val(userId);
// 						$("#frm").attr("action","/userAllList");
						$("#frm").submit();
						
				});
			});
		</script>
		  <%
      pageContext.getRequest().equals(request);
      pageContext.getSession().equals(session);
      
      // 1
      request.getContextPath();
      ((HttpServletRequest)pageContext.getRequest()).getContextPath();
      application.getContextPath();
      
      // 2
      ((HttpServletRequest)pageContext.getRequest()).getContextPath();
      pageContext.getServletContext().getContextPath();
      
      // el은 request나 context에 직접 접근이 불가능하다
   %>
   
		<form id="frm" action="${cp }/user" method ="get">
			<input type="hidden" id="userId" name ="userId"/>
		</form>
</body>
</html>
<%-- localhost/module/main.jsp --%>