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
    @Nested
    class testArea {
        @Test
        void testCircleArea() throws Exception{
            MockHttpServletRequestBuilder request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "circle")
                    .param("radius","4");
//                    .param("width","3")
//                    .param("height", "10");


            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Area of a circle with a radius of 4 is 50.26548"));
        }
        @Test
        void testCircleMethodWithRectangleType() throws Exception{
                MockHttpServletRequestBuilder request = post("/math/area")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("type", "rectangle")
                        .param("radius","4");
//                    .param("width","3")
//                    .param("height", "10");

                mvc.perform(request)
                        .andExpect(status().isOk())
                        .andExpect(content().string("Invalid"));
        }
        @Test
        void testCircleMethodWithNonIntegerRadius() throws Exception{
            MockHttpServletRequestBuilder request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "circle")
                    .param("radius","x");
//                    .param("width","3")
//                    .param("height", "10");

            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Invalid"));
        }
        @Test
        void testRectangleArea() throws Exception{
            MockHttpServletRequestBuilder request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "rectangle")
                    //.param("radius","4");
                    .param("width","3")
                    .param("height", "10");

            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Area of a 3x10 rectangle is 30"));
        }
        @Test
        void testRectangleAreaWithCircle() throws Exception{
            MockHttpServletRequestBuilder request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "circle")
                    //.param("radius","4");
                    .param("width","3")
                    .param("height", "10");

            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Invalid"));
        }
        @Test
        void testRectangleMethodWithNonIntegerWidthOrHeight() throws Exception{
            MockHttpServletRequestBuilder request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "rectangle")
                    //.param("radius","x");
                    .param("width","x")
                    .param("height", "10");

            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Invalid"));

            request = post("/math/area")
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .param("type", "rectangle")
                    //.param("radius","x");
                    .param("width","4")
                    .param("height", "y");

            mvc.perform(request)
                    .andExpect(status().isOk())
                    .andExpect(content().string("Invalid"));
        }
    }
}
