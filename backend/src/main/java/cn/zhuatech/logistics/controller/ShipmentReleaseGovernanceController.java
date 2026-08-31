/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.controller;

import cn.zhuatech.logistics.common.ApiResponse;
import cn.zhuatech.logistics.service.ShipmentReleaseGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enterprise/logistics")
public class ShipmentReleaseGovernanceController {
    private final ShipmentReleaseGovernanceService service;
    public ShipmentReleaseGovernanceController(ShipmentReleaseGovernanceService service) { this.service = service; }

    @PostMapping("/shipment-release-governance")
    public ApiResponse<ShipmentReleaseGovernanceService.Assessment> assess(
        @Valid @RequestBody ShipmentReleaseGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
