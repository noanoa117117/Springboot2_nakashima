package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.user.model.MUser;
import com.example.domain.user.service.UserService;
import com.example.form.UserDetailForm;

@Controller
@RequestMapping("/user")
public class UserDetailController {

	@Autowired
	private UserService userService;

	@Autowired
	private ModelMapper modelMapper;

	/* ユーザー詳細画面を表示  URLの変数の値を受け取るために@PathVariableアノテーションでURLの変数名を指定*/
	@GetMapping("/detail/{userId:.+}") //←メールアドレス形式の為正規表現
	public String getUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {

		// ユーザーを一件取得
		MUser user = userService.getUserOne(userId);
		user.setPassword(null);

		// MUserをformに変換
		form = modelMapper.map(user, UserDetailForm.class);

		// Modelに登録
		model.addAttribute("userDetailForm", form);

		// ユーザー詳細画面を表示
		return "user/detail";
	}
	
	/*ユーザー更新処理 PostMappingのparamsはbuttonタグのname属性と一致させる*/
	@PostMapping(value="/detail",params="update")
	public String updateUser(UserDetailForm form,Model model) {
		//ユーザーを更新
		userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		
		//ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
	
	/*ユーザー削除処理*/
	@PostMapping(value="/detail",params="delete")
	public String deleteUser(UserDetailForm form,Model model) {
		//削除
		userService.deleteUserOne(form.getUserId());
		
		//ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
}
