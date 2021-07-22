package com.edytagodniak.releasemanager.system;

import com.edytagodniak.releasemanager.api.ServiceDeployInformation;
import com.edytagodniak.releasemanager.configuration.TestRedisConfiguration;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest(classes = TestRedisConfiguration.class)
@AutoConfigureMockMvc
public class DeployManagerIntegrationTest {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void end2endServiceTest() throws Exception {

        //given
        ServiceDeployInformation information1 = new ServiceDeployInformation("service A", 1);
        ServiceDeployInformation information2 = new ServiceDeployInformation("service B", 1);
        ServiceDeployInformation information3 = new ServiceDeployInformation("service A", 2);
        ServiceDeployInformation information4 = new ServiceDeployInformation("service B", 1);

        //when
        sendRequest(mapper.writeValueAsString(information1));
        MockHttpServletResponse responseAfterAddingNewVersion = sendRequest(mapper.writeValueAsString(information2));
        sendRequest(mapper.writeValueAsString(information3));
        MockHttpServletResponse responseAfterAddingSameVersion = sendRequest(mapper.writeValueAsString(information4));


        //then
        assertEquals(responseAfterAddingNewVersion.getStatus(), 200);
        assertEquals(responseAfterAddingSameVersion.getStatus(), 200);
        assertEquals(responseAfterAddingNewVersion.getContentAsString(), "2");
        assertEquals(responseAfterAddingSameVersion.getContentAsString(), "3");
    }

    private MockHttpServletResponse sendRequest(String body) throws Exception {
        return mockMvc.perform(post("/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(body))
                .andReturn()
                .getResponse();
    }
}
