/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.controller;
import cn.zhuatech.logistics.common.ApiResponse;import cn.zhuatech.logistics.service.DeliveryExceptionService;import jakarta.validation.Valid;import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/logistics/insights/delivery-exception") public class DeliveryExceptionController {private final DeliveryExceptionService service;public DeliveryExceptionController(DeliveryExceptionService service){this.service=service;}@PostMapping ApiResponse<DeliveryExceptionService.Result> evaluate(@Valid @RequestBody DeliveryExceptionService.Request request){return ApiResponse.ok(service.evaluate(request));}}
