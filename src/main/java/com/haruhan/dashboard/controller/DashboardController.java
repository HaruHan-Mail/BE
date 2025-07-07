package com.haruhan.dashboard.controller;

import com.haruhan.common.error.StatusCode;
import com.haruhan.common.error.dto.Message;
import com.haruhan.dashboard.dto.DashboardResDto;
import com.haruhan.dashboard.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dashboard")
@CrossOrigin
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<Message> getDashboardInfo(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        DashboardResDto dashboardResDto = dashboardService.getDashboardInfo(page, size);
        return ResponseEntity.ok(new Message(StatusCode.OK, dashboardResDto));
    }


}
