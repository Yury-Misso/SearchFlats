package by.itacademy.report.controller;


import by.itacademy.report.core.etypes.ReportType;
import by.itacademy.report.core.dto.ReportDetails;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1")
public class ReportController {


    @GetMapping("/report/{type}")
    public ResponseEntity<String> prepareReport(@PathVariable ReportType type,
                                                @Validated @RequestBody ReportDetails reportDetails) {



        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(reportDetails.toString() + " - " + type.toString());

    }


}
