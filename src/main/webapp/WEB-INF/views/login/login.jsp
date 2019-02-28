
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

    <title>Signin Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">

    <!-- Custom styles for this template -->
    <link href="/css/sign.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
  </head>

  <body>
  <%-- 파라미터 전송을 위해 신경쓸 부분 
  		1.어디로 보내는지 ?? : form action 속성
  		--> 로그인 처리 요청 : LoginServlet doPost
  		2.어떻게 보낼지  ?? : form  method 속성
  		--> post : 사용자 비밀번호 같이 보안 이슈가 발생 할 수 이쓰으므로
  					get 방식으로 보내지 않는다.
  		3.뭘 보낼지 ?? : input,select, textarea 의 name 속성
  		-->userId,passwrod
  
  --%>

    <div class="container">

      <form class="form-signin" action="/login" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="inputEmail" class="sr-only">Email address</label>
        <input name="userId"  type="text" id="userId" class="form-control" placeholder="Email address" required autofocus>
        <label for="inputPassword" class="sr-only" >Password</label>
        <input name="pass" value="brown1234" type="password" id="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
          <label>
            <input id="rememberme" type="checkbox" value="remember-me" > Remember me
          </label>
        </div>a
        <button class="btn btn-lg btn-primary btn-block" type="button" id ="signin">Sign in</button>
      </form>

    </div> <!-- /container -->


  </body>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
  <script src="${cp}/js/cookieUtil.js"></script>
  <script src="${cp}/js/js.cookie.js"></script>
  <script>
  	$(document).ready(function(){
  		console.log("cp : ${cp}");
  		//userId 쿠키 값이 있을경우 userId input에 설정
  		if(Cookies.get("userId")){
  			$("#userId").val(Cookies.get("userId"));
  			$("#rememberme").prop("checked",true);
  		}
  		
  		
  		
  		//signin button 클릭 이벤트 핸들러
  		$("#signin").click(function(){
  			console.log("sign click");
  			//1.remeberme 체크박스가 체크 되었을 경우
  			//사용자 아이디 input에 저장된 값을
  			//Cookie이름 : userId/ cookieValue : 입력된 값 오토 쿠키 를 생성
  			//유효기간 30일날로 설정하는 로직
  			//2.rememberme 체크박스가 되어있지 않을경우
  			//cookie이름 : userId --> cookie삭제
  			
  			//remeberme 체크박스 체크 된 경우
  			if($("#rememberme").prop("checked")){
  				Cookies.set("userId" , $("#userId").val(),{expires : 30});
  				Cookies.set("rememberme" , "y",{expires : 30});
  			}
  			else{
  				Cookies.remove("userId");
  				Cookies.remove("rememberme");
  			}
  			
  			$("form").submit();
  		});
  	});
  </script>
</html>
