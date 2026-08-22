/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.logistics;
import cn.zhuatech.logistics.service.DeliveryExceptionService;import org.junit.jupiter.api.Test;import static org.junit.jupiter.api.Assertions.*;
class DeliveryExceptionServiceTests {private final DeliveryExceptionService service=new DeliveryExceptionService();@Test void intervenesForTemperatureExcursion(){var r=service.evaluate(new DeliveryExceptionService.Request("S1",10,4,true,false,false,true,false));assertEquals("INTERVENE",r.status());}@Test void monitorsOnTimeShipment(){var r=service.evaluate(new DeliveryExceptionService.Request("S2",48,0,false,false,false,false,true));assertEquals("MONITOR",r.status());}}
