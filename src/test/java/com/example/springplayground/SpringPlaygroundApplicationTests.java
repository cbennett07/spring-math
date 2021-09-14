package com.example.springplayground;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagesController.class)
public class SpringPlaygroundApplicationTests {

    @Autowired
    MockMvc mvc;

    //TESTS
    @Test //home page
    public void testHomePage() throws Exception {
        RequestBuilder request = get("/hello");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("Hello World!"));
    }

    @Test
    public void testIndividualExample() throws Exception{
        RequestBuilder request = get("/individual-example/foo/bar");

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("q:foo from:bar"));
    }
    @Test
    public void testHeaders() throws Exception {
        this.mvc.perform(get("/header").header("Host", "example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("example.com"));
    }

    @Nested
    class MathTests{
        @Test
        public void testAdd() throws Exception{
            mvc.perform(get("/math/calculate?operation=add&x=4&y=6"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 + 6 = 10"));
        }
        @Test
        public void testSubtract() throws Exception{
            mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6 "))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 - 6 = -2"));
        }
        @Test
        public void testMultiply() throws Exception{
            mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6 "))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 * 6 = 24"));
        }
        @Test
        public void testDivide() throws Exception{
            mvc.perform(get("/math/calculate?operation=divide&x=30&y=5 "))
                    .andExpect(status().isOk())
                    .andExpect(content().string("30 / 5 = 6"));
        }
        @Test
        public void testDefault() throws Exception{
            mvc.perform(get("/math/calculate?x=30&y=5"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("30 + 5 = 35"));
        }

    }
    @Nested
    class mathSumPost{
        @Test
        void testMathSum() throws Exception{
            mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 + 5 + 6 = 15"));

        }
    }
    @Nested
    class volumeWithPathTests{
        @Test
        void testVolume() throws Exception{
            mvc.perform(post("/math/volume/3/4/5"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
        }

    }

}
