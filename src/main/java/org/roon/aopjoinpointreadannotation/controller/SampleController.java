package org.roon.aopjoinpointreadannotation.controller;

import org.roon.aopjoinpointreadannotation.aspect.CheckRole;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/")
    @CheckRole(roles = {"admin"})
    public String sample() {
        return "greeting project administrator";
    }

    @GetMapping("/exception")
    @CheckRole(roles = {"guest", "member"})
    public String throwException() throws RuntimeException {
        throw new RuntimeException("no authorization");
    }

}
