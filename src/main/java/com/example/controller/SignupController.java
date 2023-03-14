package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/user")
@Slf4j // 簡単にログ出力
public class SignupController {

	@Autowired
	private UserApplicationService userApplicationService;

	/* ユーザー登録画面を表示 */
	@GetMapping("/signup") // @ModelAttributeはmodel.addAttribute("signupForm",form)みたいな
	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		// 性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		model.addAttribute("genderMap", genderMap);

		return "user/signup";
	}

	/* ユーザー登録処理 */
	@PostMapping("/signup") //BindingResultでバインド、バリデーションエラー確認
	public String postSignup(Model model, Locale locale, @ModelAttribute SignupForm form, BindingResult bindingResult) {
		//入力チェック結果
		if(bindingResult.hasErrors()) {
			return getSignup(model,locale,form);
		}
		
		
		log.info(form.toString());
		return "redirect:/login";
	}
}
