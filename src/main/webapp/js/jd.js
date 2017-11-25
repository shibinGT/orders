	
	$(document).ready(function(){
		
		$('#user').focus(function() {
			$('.span1').text('4-20个字符，支持汉字、字母、数字、下划线').show();
		})
		$('#user').blur(function() {
			var str = $('#user').val();
			if(chek(str)) {
			
				$('.span1').hide(); //为空隐藏
			} else if(est(str)) {
			
				$('.span1').html("<span style='background-color:cyan;'>用户名可用</span>").show();//true
			} else {
				$('.span1').html("<span style='background-color:red;'>格式不正确</span>").show();//false
			}
		})
		
		/*
		 * 校验是否为空
		 */
		function est(name) {
			var userEst = /^\w{4,20}$/;
			if(userEst.test(name)) {
				return true;
			} else {
				return false;
			}
		}
		/*
		  * 校验输入
		  */
		function chek(str1) {
			if(str1 == null || str1 == undefined) {
				return true;
			}
			str1 = str1.trim();
			if(str1 == "") {
				return true;
			}
			return false;
		}
		/**
		 * password
		 */
		$('#pass1').focus(function() {
			$('.span2').text('6-12个字符，支持汉字、字母、数字、下划线').show();
		})
		
		$('#pass1').blur(function() {
			 str1 = $('#pass1').val();
			if(chek(str1)) {
				$('.span2').hide(); //为空隐藏
			} else if(est1(str1)) {
				$('.span2').html("<span style='background-color:cyan;'>密码可用</span>").show();//true
			} else {
				$('.span2').html("<span style='background-color:red;'>密码格式不正确</span>").show();//false
			}
		})
		
		function est1(name) {
			var userEst = /^\w{6,12}$/;
			if(userEst.test(name)) {
				return true;
			} else {
				return false;
			}
		}
		/*
		 * password2
		 */
		$('#pass2').focus(function() {
			$('.span3').text('重新输入密码').show();
		})
		
		$('#pass2').blur(function() {
			 str2 = $('#pass2').val();
			if(chek(str2)){
				$('.span3').hide(); //为空隐藏
			}else if(str2==str1){
				$('.span3').html("<span style='background-color:cyan;'>√</span>").show();
			}else{
				$('.span3').html("<span style='background-color:red;'>两次输入不正确</span>").show();
			}
		})})