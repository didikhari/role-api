package web.id.didikhari.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping(value="/user")
	public Map<String, String> user(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "success");
		result.put("message", "Sukses Request User role");
		return result;
	}
	
	@GetMapping(value="/admin")
	public Map<String, String> admin(){
		Map<String, String> result = new HashMap<String, String>();
		result.put("status", "success");
		result.put("message", "Sukses Request Admin role");
		return result;
	}
}
