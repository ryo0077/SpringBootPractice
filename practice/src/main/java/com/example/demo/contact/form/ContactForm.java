package com.example.demo.contact.form;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ContactForm implements Serializable {

    @NotBlank(message = "姓は必須です")
    private String lastName;

    @NotBlank(message = "名は必須です")
    private String firstName;

    @NotBlank(message = "メールアドレスは必須です")
    @Email(message = "メールアドレスの形式が不正です")
    private String email;
    @Pattern(regexp = "^[0-9\\-]{10,13}$", message = "電話番号は数字とハイフンのみで入力してください")
    private String phone;

    
    @Pattern(regexp = "^(\\d{3}-?\\d{4})?$", message = "郵便番号は7桁です（例: 123-4567）")
    private String zipCode;

    @NotBlank(message = "住所は必須です")
    private String address;

    @NotBlank(message = "建物名は必須です")
    private String buildingName;

    @NotBlank(message = "お問い合わせ種別を選択してください")
    private String contactType;

    @NotBlank(message = "お問い合わせ内容は必須です")
    @Size(min = 10, max = 1000, message = "お問い合わせ内容は10文字以上、1000文字以内で入力してください")
    private String body;
}

