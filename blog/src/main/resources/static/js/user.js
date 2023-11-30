let index = {
	
	init: function(	){
		$("#btn-save").on("click", ()=>{
			this.save();
		});
	},
	
	save: function(){
		//alert('user의 save 함수 호출됨');
		
		let data = {
			userName: $("#userName").val(),
			password: $("#password").val(),
			email: $("#email").val()
		}
		
		console.log(data);
		
	}
	
	
}

index.init();