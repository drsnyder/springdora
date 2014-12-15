package com.wikia.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdminController {

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String index() {
        return "hello, admin";
    }
}
