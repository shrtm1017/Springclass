<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>



		<h1 class="page-header">전체 사용자 정보조회(tiles)</h1>

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
				  <c:forEach items="${userAllList }" var ="user" varStatus="i">
                <%-- 향상된 for문은 begin이 없어서 i값을 구할 수 없음으로 varStatus를 이용해서 구한다 --%> 
                   <tr class='userTr' data-userid='${user.userId }'>
                      <td> ${i.index } </td>
                      <td> ${user.userId }</td>
                      <td> ${user.userNm }</td>
                      <td> ${user.alias }</td>
                      <td> ${user.reg_dt_fmt }</td>
                   </tr>
               </c:forEach>

				</tbody>
			</table>
		</div>
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
   
		<form id="frm" action="${cp }/user/user" method ="get">
			<input type="hidden" id="userId" name ="userId"/>
		</form>
<%-- localhost/module/main.jsp --%>