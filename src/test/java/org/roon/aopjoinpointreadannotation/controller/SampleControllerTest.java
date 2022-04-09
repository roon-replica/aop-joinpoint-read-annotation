package org.roon.aopjoinpointreadannotation.controller;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.roon.aopjoinpointreadannotation.aspect.CheckRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class SampleControllerTest {
//    @RestController
//    private class SampleController {
//
//        @GetMapping("/")
//        @CheckRole(roles = {"admin"})
//        public String sample() {
//            return "greeting project administrator";
//        }
//
//        @GetMapping("/exception")
//        @CheckRole(roles = {"guest", "member"})
//        public String throwException() throws RuntimeException {
//            throw new RuntimeException("no authorization");
//        }
//    }

    @Autowired
    private SampleController sampleController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        this.mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
    }

    @Test
    public void testSampleAdvice() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(200));
    }

    @Test
    public void testExceptionAdvice() throws Exception{
        mockMvc.perform(get("/exception"));
    }
}