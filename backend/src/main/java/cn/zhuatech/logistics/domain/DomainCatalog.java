/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics.domain;
import org.springframework.stereotype.Component;
import java.util.List;
@Component public class DomainCatalog {
    public String systemName(){return "知华 Logistics 物流控制塔平台";}
    public String sceneName(){return "订单、运输、节点、路由与异常处置";}
    public List<SeedItem> seedItems(){return List.of(
        new SeedItem("LOGISTICS-20260801-001","华东线路暴雨改道调度","处理中","运输调度组","紧急"),
        new SeedItem("LOGISTICS-20260801-002","冷链温控偏差复核","待处理","质量保障组","高"),
        new SeedItem("LOGISTICS-20260801-003","七月承运商准时率复盘","已完成","运力管理组","中"),
        new SeedItem("LOGISTICS-20260801-004","跨仓波次资源协调","处理中","控制塔运营组","高"));}
    public List<String> recommendedActions(){return List.of("优先处置天气与温控高风险运输","重算受影响订单路由和到达时间","通知客户并跟踪关键节点回传");}
    public record SeedItem(String recordNo,String title,String status,String owner,String priority){}
}
