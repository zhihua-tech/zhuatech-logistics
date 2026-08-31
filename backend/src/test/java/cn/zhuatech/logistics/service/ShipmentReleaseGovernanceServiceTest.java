/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.service;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class ShipmentReleaseGovernanceServiceTest {
    private final ShipmentReleaseGovernanceService service = new ShipmentReleaseGovernanceService();

    @Test void dispatchesCompliantShipment() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-001", true, true, false, false, false, false, true, 1000, 1200, false, false, false, true));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.DISPATCH);
    }

    @Test void blocksCarrierDocumentAndCapacityRisks() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-002", false, false, true, false, true, false, false, 1500, 1000, false, false, false, true));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.BLOCKED);
        assertThat(result.blockers()).hasSize(6);
    }

    @Test void reviewsColdChainRouteAndCustody() {
        var result = service.assess(new ShipmentReleaseGovernanceService.Request(
            "SHP-003", true, true, false, false, false, false, true, 500, 800, true, false, true, false));
        assertThat(result.decision()).isEqualTo(ShipmentReleaseGovernanceService.Decision.REVIEW);
        assertThat(result.actions()).hasSize(3);
    }
}
