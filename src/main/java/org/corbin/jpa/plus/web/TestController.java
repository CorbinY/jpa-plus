package org.corbin.jpa.plus.web;

import org.corbin.jpa.plus.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping("/test")
    public void testqw() {
        org.corbin.jpa.plus.entity.Test t = new org.corbin.jpa.plus.entity.Test();
        t.setId(1);
        t.setName("yin");
        testService.save(t);
    }
}
