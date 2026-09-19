/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class ShipmentReleaseGovernanceServiceTest {
    private final ShipmentReleaseGovernanceService service = new ShipmentReleaseGovernanceService();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void dispatchesCompliantShipment() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-001", true, true, false, false, false, false, true, 1000, 1200, false, false, false, true));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.DISPATCH);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void blocksCarrierDocumentAndCapacityRisks() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-002", false, false, true, false, true, false, false, 1500, 1000, false, false, false, true));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(6);
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void reviewsColdChainRouteAndCustody() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-003", true, true, false, false, false, false, true, 500, 800, true, false, true, false));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(3);
    }
}
