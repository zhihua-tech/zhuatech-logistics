# 企业级物流发运治理

上海如静知华信息科技有限公司（[知华科技](https://www.zhuatech.cn/)）为 Logistics 开源版增加发运门禁。

`POST /api/enterprise/logistics/shipment-release-governance` 检查承运商资质、运输单证、危险品、海关、保险、容量、冷链、路线风险和货权交接，返回 `DISPATCH / REVIEW / BLOCKED`。

生产应用应与 TMS、WMS、关务、保险和 IoT 温控设备联动，并保存装运版本及每次人工复核意见。
