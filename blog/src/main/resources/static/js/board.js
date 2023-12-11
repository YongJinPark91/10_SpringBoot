let index = {
	
	init: function(	){
		$("#btn-save").on("click", ()=>{ // 화살표 함수를 사용한 이유는 this를 바인딩 하기 위함이다.
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			title: $("#title").val(),
			content: $("#content").val()
		};

		$.ajax({
			type : "POST",
			url : "/api/board",
			data : JSON.stringify(data), 
			contentType : "application/json; charset=utf-8",
			dataType : "json",
		}).done(function(resp){
			alert("글쓰기를 완료하였습니다.");
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error));
			
		});
		
	}
}

index.init();