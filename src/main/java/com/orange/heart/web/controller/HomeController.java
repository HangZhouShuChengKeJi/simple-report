package com.orange.heart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author 小天
 * @date 2019/2/15 16:21
*/
@Controller
public class HomeController {


    @RequestMapping("/index.htm")
    public String index() {
        return "index";
    }
}
