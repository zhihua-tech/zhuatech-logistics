/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.service;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class LoadPlanService {
    public LoadDecision evaluate(LoadRequest request) {
        double weightRate = Math.round(request.plannedWeightKg() * 1000.0 / request.weightCapacityKg()) / 10.0;
        double volumeRate = Math.round(request.plannedVolumeM3() * 1000.0 / request.volumeCapacityM3()) / 10.0;
        List<String> reasons = new ArrayList<>();
        if (weightRate > 100) reasons.add("计划重量超过车辆核定载重");
        if (volumeRate > 100) reasons.add("计划体积超过车辆有效容积");
        if (request.hazardousGoods() && !request.separationConfirmed()) reasons.add("危险品隔离和配载条件未确认");
        if (request.stopCount() > 15) reasons.add("配送停靠点过多，建议拆分波次或线路");
        String decision = weightRate > 100 || volumeRate > 100
            || (request.hazardousGoods() && !request.separationConfirmed()) ? "BLOCK"
            : weightRate > 90 || volumeRate > 90 || request.stopCount() > 15 ? "REVIEW" : "READY";
        if (reasons.isEmpty()) reasons.add("重量、体积、线路与货物约束满足装载条件");
        return new LoadDecision(weightRate, volumeRate, decision, reasons);
    }

    public record LoadRequest(@NotNull @Positive Integer weightCapacityKg,
        @NotNull @Min(0) Integer plannedWeightKg,
        @NotNull @Positive Integer volumeCapacityM3,
        @NotNull @Min(0) Integer plannedVolumeM3,
        @NotNull @Min(1) @Max(1000) Integer stopCount,
        @NotNull Boolean hazardousGoods, @NotNull Boolean separationConfirmed) {}
    public record LoadDecision(double weightRate, double volumeRate, String decision, List<String> reasons) {}
}
