package com.example.demo.form;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data

public class AdminSigninForm {
	
	@NotBlank(message = "メールアドレスを入力してください。")
	@Email(message = "メールアドレスの形式が正しくありません。")
	private String email;
	
	@NotBlank(message = "パスワードを入力してください。")
	private String password;

}
