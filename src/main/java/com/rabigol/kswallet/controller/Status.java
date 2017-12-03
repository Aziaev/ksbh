package com.rabigol.kswallet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Status {

    @RequestMapping("/status")
    public String index() {
        return "Greetings from kswallet!";
    }
}
