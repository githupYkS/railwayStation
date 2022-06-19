package com.gx.railwaystation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/conductor")
public class StaffController {

    @RequestMapping("/staff")
    public String staff(){
        return "conductor/staff";
    }
}
