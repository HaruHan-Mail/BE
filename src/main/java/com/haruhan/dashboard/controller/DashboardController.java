package com.haruhan.dashboard.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.dashboard.dto.DashboardResDto;
import com.haruhan.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Message> getDashboardInfo() {
        DashboardResDto dashboardResDto = dashboardService.getDashboardInfo();
        return ResponseEntity.ok(new Message(StatusCode.OK, dashboardResDto));
    }
}
