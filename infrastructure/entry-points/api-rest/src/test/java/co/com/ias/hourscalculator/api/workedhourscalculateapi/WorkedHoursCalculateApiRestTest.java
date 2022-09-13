package co.com.ias.hourscalculator.api.workedhourscalculateapi;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import co.com.ias.hourscalculator.usecase.workedhourscalculate.utils.dto.ResponseHoursCalculated;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class,
    WorkedHoursCalculateApiRestTest.class}, webEnvironment = RANDOM_PORT)
class WorkedHoursCalculateApiRestTest {

    @Autowired
    private WebTestClient client;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
    }

    @Test
    void getHoursByTechnicianIdAndWeek() throws JsonProcessingException {

        //given
        ResponseHoursCalculated responseHoursCalculated = ResponseHoursCalculated.builder()
            .normal(7200)
            .overtime(0)
            .night(0)
            .overtimeNight(0)
            .sunday(0)
            .overtimeSunday(0)
            .build();
        String technicianId = "test";
        int week = 1;

        //when
        client.get().uri("/api/hours-calculate/technician/" + technicianId + "/week/" + week)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            //expected
            .expectStatus().isOk()
            .expectBody()
            .jsonPath("$.normal").isEqualTo(7200)
            .jsonPath("$.overtime").isEqualTo(0)
            .jsonPath("$.night").isEqualTo(0)
            .jsonPath("$.overtimeNight").isEqualTo(0)
            .jsonPath("$.sunday").isEqualTo(0)
            .jsonPath("$.overtimeSunday").isEqualTo(0)
            .json(objectMapper.writeValueAsString(responseHoursCalculated));

    }
}
