/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.controller;

import cn.zhuatech.logistics.common.ApiResponse;
import cn.zhuatech.logistics.service.LoadPlanService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController
@RequestMapping("/api/admin/load-plan")
public class LoadPlanController {
    private final LoadPlanService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public LoadPlanController(LoadPlanService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping
    ApiResponse<LoadPlanService.LoadDecision> evaluate(
        @Valid @RequestBody LoadPlanService.LoadRequest request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
