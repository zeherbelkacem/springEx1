package com.fms.springEx1.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fms.springEx1.Exceptions.NotFoundEntityException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NotFoundEntityException.class)
	public String articleNotFoundException(NotFoundEntityException ex, Model model) {
		model.addAttribute("message", ex.getMessage());
		model.addAttribute("code", ex.getCode());
		return "error";
		
	}
}
