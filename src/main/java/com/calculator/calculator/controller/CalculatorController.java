package com.calculator.calculator.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.function.DoubleBinaryOperator;
import java.util.function.DoubleFunction;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.NumberUtils;

import com.calculator.calculator.Encryptor;
import com.calculator.calculator.enumeration.EOperations;

@Controller
public class CalculatorController {

	private final static String INDEX = "index";

	@GetMapping("/")
	public String home() {

		return INDEX;

	}

	@PostMapping("/calculate")
	public String calculate(String operation, Double result, Double var, Model m, RedirectAttributes r) {

		long start = System.nanoTime();
		EOperations eo = EOperations.getOperation(operation);

		DoubleBinaryOperator f2 = null;
		DoubleFunction f1 = null;

		switch (eo) {
		case SUM:
			f2 = (a, b) -> a + b;
			break;
		case REST:
			f2 = (a, b) -> a - b;
			break;
		case MULT:
			f2 = (a, b) -> a * b;
			break;
		case DIVI:
			f2 = (a, b) -> a / b;
			break;
		case SEN:
			f1 = a -> Math.sin(a);
			break;
		case COS:
			f1 = a -> Math.cos(a);
			break;
		case TAN:
			f1 = a -> Math.tan(a);
			break;
		case LOG10:
			f1 = a -> Math.log10(a);
			break;
		case POW:
			f2 = (a, b) -> Math.pow(a, b);
			break;
		case SQRT:
			f1 = (a) -> Math.sqrt(a);
			break;
		case CE:
			f2 = (a, b) -> 0;
			f1 = a -> 0;
			break;
		default:
			f2 = null;
			f1 = null;
			break;
		}

		long end = System.nanoTime();
		long executonTime = end - start;

		r.addFlashAttribute("result", f2 != null ? f2.applyAsDouble(result, var) : f1 != null ? f1.apply(var) : 0);
		r.addFlashAttribute("executonTime", executonTime);

		return "redirect:/";

	}

	@PostMapping("/encrypt")
	public String encrypt(String word, String action, RedirectAttributes r) {
		r.addFlashAttribute("word", word);
		r.addFlashAttribute("cryp", "encriptar".equals(action) ? Encryptor.encrypt(word) : Encryptor.decrypt(word));
		return "redirect:/";

	}
	
	
	@PostMapping("/convert")
	public String convertSystem(String number, RedirectAttributes r){
	
		Map<String , String>  result = new HashMap();
//		result.put("DECIMAL", Integer.TO);
//		result.put("BINARIO", Integer.toBinaryString(number));
//		result.put("OCTAL", Integer.toOctalString(i));
//		result.put("HEXADECIMAL", value);
//		result.put("BCD", value);
		
		r.addFlashAttribute("convert",result);
		
		
		
		return "redirect:/";
	}

}
