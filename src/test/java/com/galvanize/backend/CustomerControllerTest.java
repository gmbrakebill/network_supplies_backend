package com.galvanize.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class CustomerControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void testCustomer() throws Exception
    {
        MockHttpServletRequestBuilder request1 = get("/hello")
                .accept(MediaType.APPLICATION_JSON_UTF8);
        this.mvc.perform(request1)
                .andExpect(status().isOk())
                .andExpect(content().string("hello from Get!"));
    }
}
