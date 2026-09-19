/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.controller;

import cn.zhuatech.logistics.common.ApiResponse;
import cn.zhuatech.logistics.service.ShipmentReleaseGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/enterprise/logistics")
public class ShipmentReleaseGovernanceController {
    private final ShipmentReleaseGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public ShipmentReleaseGovernanceController(ShipmentReleaseGovernanceService service) { this.service = service; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/shipment-release-governance")
    public ApiResponse<ShipmentReleaseGovernanceService.Assessment> assess(
        @Valid @RequestBody ShipmentReleaseGovernanceService.Request request) {
        return ApiResponse.ok(service.assess(request));
    }
}
