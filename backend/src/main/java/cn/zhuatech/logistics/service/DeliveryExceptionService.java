/* Copyright 2026 上海如静知华信息科技有限公司 */
package cn.zhuatech.logistics.service;
import jakarta.validation.constraints.*;import org.springframework.stereotype.Service;import java.util.*;
@Service public class DeliveryExceptionService {
 public Result evaluate(Request r){int score=0;List<String> reasons=new ArrayList<>();if(r.delayHours()>0){score+=Math.min(40,r.delayHours()*5);reasons.add("预计延误 "+r.delayHours()+" 小时");}if(r.temperatureExcursion()){score+=60;reasons.add("运输温度发生偏离");}if(r.damageReported()){score+=70;reasons.add("货物损坏已上报");}if(r.customsHold()){score+=40;reasons.add("货物处于海关滞留状态");}if(r.highValue())score+=10;if(!r.alternativeRouteAvailable()&&r.hoursToPromise()<12)score+=20;score=Math.min(100,score);String status=score>=70?"INTERVENE":score>=30?"EXPEDITE":"MONITOR";if(reasons.isEmpty())reasons.add("运输节点与承诺时间正常");return new Result(score,status,reasons,status.equals("INTERVENE")?"立即通知客户并启动异常索赔或替代交付":"持续跟踪节点并刷新预计到达时间");}
 public record Request(@NotBlank String shipmentId,@Min(0) int hoursToPromise,@Min(0) int delayHours,@NotNull Boolean temperatureExcursion,@NotNull Boolean damageReported,@NotNull Boolean customsHold,@NotNull Boolean highValue,@NotNull Boolean alternativeRouteAvailable){}
 public record Result(int exceptionScore,String status,List<String> reasons,String action){}
}
