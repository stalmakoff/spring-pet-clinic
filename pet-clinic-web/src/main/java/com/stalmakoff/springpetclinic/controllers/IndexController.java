package com.stalmakoff.springpetclinic.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IndexController {

    @RequestMapping({"", "/", "index", "index.html"})
    public String index() {
        log.debug("Getting Index page");
        return "index";
    }

    @RequestMapping({"/oups"})
    public String oupsHandler(){

        return "notImplemented";
    }


}
