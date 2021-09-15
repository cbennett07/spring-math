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

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PagesController.class)
public class SpringPlaygroundApplicationTests {

    @Autowired
    MockMvc mvc;

    //TESTS
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
            mvc.perform(get("/math/calculate?operation=subtract&x=4&y=6"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 - 6 = -2"));
        }
        @Test
        public void testMultiply() throws Exception{
            mvc.perform(get("/math/calculate?operation=multiply&x=4&y=6"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 * 6 = 24"));
        }
        @Test
        public void testDivide() throws Exception{
            mvc.perform(get("/math/calculate?operation=divide&x=30&y=5"))
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
    @Nested //Math Sum exercise
    class mathSumPost{
        @Test
        void testMathSum() throws Exception{
            mvc.perform(post("/math/sum?n=4&n=5&n=6"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("4 + 5 + 6 = 15"));

        }
    }
    @Nested // Math Volume exercise
    class volumeWithPathTests{
        @Test
        void testVolume() throws Exception{
            mvc.perform(post("/math/volume/3/4/5"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("The volume of a 3x4x5 rectangle is 60"));
        }
    }
    @Nested // Math Calculate Area exercise
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
    @Nested
    class cookiesAndHeaders{
        //test getting cookie info
        @Test
        void testCookies() throws Exception{
            mvc.perform(get("/cookie")
                            .cookie(new Cookie("foo", "bar")))
                    .andExpect(status().isOk())
                    .andExpect(content().string("bar"));
        }
        //test getting header info
        @Test
        public void testHeaders() throws Exception {
            mvc.perform(get("/header")
                            .header("Host", "cnn.com"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("cnn.com"));
        }
        //test use of Dog class
        @Test
        void testDogQuery() throws Exception{
            mvc.perform(get("/dog/message?name=Lulu&color=black&weight=4"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Lulu is a good black dog"));

            mvc.perform(get("/dog/message?name=Rambo&color=white and brown&weight=90"))
                    .andExpect(status().isOk())
                    .andExpect(content().string("Rambo is a good white and brown dog...that weighs TOO MUCH!"));


        }
    }

}
