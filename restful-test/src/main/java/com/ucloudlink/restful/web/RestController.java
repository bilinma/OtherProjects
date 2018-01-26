package com.ucloudlink.restful.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/rest")
public class RestController {
	
	@GetMapping(value="/info/{param}")
	@ResponseBody
	public Map get(@PathVariable(value="param") String param){
		System.out.println("get "+param);
		
		Map map= new HashMap();
		map.put("Id", param);
		map.put("name", "maxb");
		return map;
	}

	
	@PostMapping(value="/info/{param}")
	@ResponseBody
	public String post(@PathVariable(value="param") String param){
		System.out.println("get "+param);
		return "/hello";
	}

	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public String get(@PathVariable("id") Integer id){
        System.out.println("get "+id);
        return "/hello";
    }
}
