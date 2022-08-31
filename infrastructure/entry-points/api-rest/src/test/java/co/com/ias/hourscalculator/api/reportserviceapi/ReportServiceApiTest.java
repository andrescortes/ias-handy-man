package co.com.ias.hourscalculator.api.reportserviceapi;

import co.com.ias.hourscalculator.model.reportservicemodel.ReportService;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(classes = {ServletWebServerFactoryAutoConfiguration.class,
    ReportServiceApiTest.class}, webEnvironment = RANDOM_PORT)
class ReportServiceApiTest {

    @Autowired
    private WebTestClient client;

    @Test
    void getReportServicesFromApi() {
        //given
        ReportService reportService = ReportService.builder()
            .reportServiceId("test")
            .technicianId("test")
            .serviceStartDate(LocalDateTime.parse("2022-01-03T07:00:00"))
            .serviceEndDate(LocalDateTime.parse("2022-01-03T09:00:00"))
            .build();
        //when
        client.post().uri("/api/report-service")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(reportService)
            .exchange()
            //expected
            .expectStatus().isCreated()
            .expectBody()
            .jsonPath("$.reportServiceId").isNotEmpty()
            .jsonPath("$.reportServiceId").isEqualTo("test")
            .jsonPath("$.technicianId").isEqualTo("test")
            .jsonPath("$.serviceStartDate").isEqualTo("2022-01-03T07:00:00")
            .jsonPath("$.serviceEndDate").isEqualTo("2022-01-03T09:00:00");
    }

}
