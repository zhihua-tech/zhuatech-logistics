/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.service;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class ShipmentReleaseGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Assessment assess(Request request) {
        List<String> blockers = new ArrayList<>();
        List<String> actions = new ArrayList<>();
        if (!request.carrierApproved()) blockers.add("承运商未通过准入或资质已失效");
        if (!request.shippingDocumentsComplete()) blockers.add("运输与交接单据不完整");
        if (request.dangerousGoods() && !request.dangerousGoodsDeclarationApproved()) blockers.add("危险品申报未批准");
        if (request.crossBorder() && !request.customsClearanceReady()) blockers.add("跨境报关资料未就绪");
        if (!request.cargoInsuranceValid()) blockers.add("货运保险无效");
        if (request.capacityKg() < request.plannedWeightKg()) blockers.add("承运容量低于计划重量");
        if (!blockers.isEmpty()) {
            actions.add("冻结发运并由物流控制塔关闭资质、单证、容量或合规问题");
            return new Assessment(Decision.BLOCKED, blockers, actions);
        }
        if ((request.coldChain() && !request.temperatureLoggerReady())
            || request.routeRiskHigh() || !request.custodyHandoverConfirmed()) {
            if (request.coldChain() && !request.temperatureLoggerReady()) actions.add("配置温度记录器并验证校准状态");
            if (request.routeRiskHigh()) actions.add("复核高风险路线和替代方案");
            if (!request.custodyHandoverConfirmed()) actions.add("确认货权与交接责任链");
            return new Assessment(Decision.REVIEW, blockers, actions);
        }
        actions.add("允许发运并冻结承运、单证、路线和交接快照");
        return new Assessment(Decision.DISPATCH, blockers, actions);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String shipmentNo, boolean carrierApproved,
                          boolean shippingDocumentsComplete, boolean dangerousGoods,
                          boolean dangerousGoodsDeclarationApproved, boolean crossBorder,
                          boolean customsClearanceReady, boolean cargoInsuranceValid,
                          @Min(0) int plannedWeightKg, @Min(0) int capacityKg,
                          boolean coldChain, boolean temperatureLoggerReady,
                          boolean routeRiskHigh, boolean custodyHandoverConfirmed) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Assessment(Decision decision, List<String> blockers, List<String> actions) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public enum Decision { DISPATCH, REVIEW, BLOCKED }
}
