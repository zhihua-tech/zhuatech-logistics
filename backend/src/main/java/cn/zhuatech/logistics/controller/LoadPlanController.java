/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.logistics.controller;

import cn.zhuatech.logistics.common.ApiResponse;
import cn.zhuatech.logistics.service.LoadPlanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/load-plan")
public class LoadPlanController {
    private final LoadPlanService service;
    public LoadPlanController(LoadPlanService service) { this.service = service; }
    @PostMapping
    ApiResponse<LoadPlanService.LoadDecision> evaluate(
        @Valid @RequestBody LoadPlanService.LoadRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
