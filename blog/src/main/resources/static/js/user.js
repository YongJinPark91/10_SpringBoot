let index = {
	
	init: function(	){
		$("#btn-save").on("click", ()=>{ // 화살표 함수를 사용한 이유는 this를 바인딩 하기 위함이다.
			this.save();
		});
		$("#btn-login").on("click", ()=>{ // 화살표 함수를 사용한 이유는 this를 바인딩 하기 위함이다.
			this.login();
		});
	},
	
	save: function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
			email: $("#email").val()
		};
		
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출(통신)
		// ajax통신을 활용해서 3개의 파라미터를 json으로 변경하여 insert 요청을 할 예정임
		// ajax가 통신을 성공하고, 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환을 해준다.
		$.ajax({
			// 회원가입 수행 요청
			type : "POST",
			url : "/api/user",
			data : JSON.stringify(data), // http body 데이터 
			contentType : "application/json; charset=utf-8", // body 데이터가 어떤타입인지 (MIME 타입이 필요하다)
			dataType : "json", // 요청을 서버로해서 응답이 왔을 때, 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경
			// dataType을 생략해도 json으로 던져준다. 그 이유는 jquery가 MIME 타입을 확인하고 자동으로 결정을 해준다.
		}).done(function(resp){
			alert("회원가입이 완료 되었습니다.");
			//console.log(resp);
			alert(resp); // Controller를 거쳐 return 값이 반환되는 모습을 볼 수 있다.
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
			
		});
		
	},
	
		login: function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
		};
		
		$.ajax({
			// 회원가입 수행 요청
			type : "POST",
			url : "/api/user/login",
			data : JSON.stringify(data), // http body 데이터 
			contentType : "application/json; charset=utf-8", // body 데이터가 어떤타입인지 (MIME 타입이 필요하다)
			dataType : "json", // 요청을 서버로해서 응답이 왔을 때, 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json 이라면) => javascript 오브젝트로 변경
		}).done(function(resp){
			alert("로그인이 완료 되었습니다.");
			//console.log(resp);
			alert(resp); // Controller를 거쳐 return 값이 반환되는 모습을 볼 수 있다.
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
			
		});
		
	}
	
	
}

index.init();