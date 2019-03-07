<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<title>Dashboard Template for Bootstrap</title>

<!-- Bootstrap core CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

<!-- Custom styles for this template -->
<link href="${cp}/css/dashboard.css"
	rel="stylesheet">

</head>
<body>
	<%-- <%List<UserVo> userList = (List<UserVo>)request.getAttribute("userList");  %> --%>
	<!-- 정적 include -->
	

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">사용자 정보조회</h1>
		<form class="navbar-form navbar-right">
			
		</form>
	</div>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">



		<form id="userUpdatefrm" action="${cp }/user/userModifyForm" method="get" class="form-horizontal" role="form">
		<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">사용자 프로필</label>
					<div class="col-sm-9">
						<img src="${cp }/user/profileImg?userId=${userVo.userId}">
					</div>
				</div>
			
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">사용자 아이디</label>
					<div class="col-sm-9">
						<label class="control-label">${userVo.userId }</label>
						<input type="hidden" id="userId" name="userId"/>
					</div>
				</div>

				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">사용자 이름</label>
					<div class="col-sm-9">
					<label class="control-label">${userVo.userNm }</label>
					
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">별명</label>
					<div class="col-sm-9">
					<label class="control-label">${userVo.alias }</label>
				
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">주소</label>
					<div class="col-sm-9">
					<label class="control-label">${userVo.addr1 }</label>
				
					</div>
				</div>
				<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">상세주소</label>
					<div class="col-sm-9">
					<label class="control-label">${userVo.addr2 }</label>
				
					</div>
				</div>
								<div class="form-group">
					<label for="userNm" class="col-sm-3 control-label">우편번호</label>
					<div class="col-sm-9">
					<label class="control-label">${userVo.zipcode}</label>
				
					</div>
				</div>
	

				<div class="form-group">
					<label for="pass" class="col-sm-3 control-label">등록인자</label>
					<div class="col-sm-9">
						<label class="control-label">${userVo.getReg_dt_fmt()}</label>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-3 col-sm-9">
						<button id="updateButton" type="submit" class="btn btn-default">사용자 수정</button>
					</div>
				</div>
		</form>
	</div>
</body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<%-- 순서 중요 --%>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>

	<script>
	
	function initData(){
		$("#userId").val("${param.userId}");
		$("#alias").val("${param.alias}");
		
	}
	$(document).ready(function(){
		initData();
		console.log(initData);
	})
		$("updateButton").on("click", function(){
			$("#userUpdatefrm").submit();
			
		})
	
	</script>
		<script >
		<c:if test="${msg !=null}">
			alert("${msg}");
		</c:if>
	</script>
</body>
</html>
