package me.sdevil507.drools.model;

import lombok.Data;

/**
 * 订单消费金额与返回积分FACT对象
 *
 * @author sdevil507
 */
@Data
public class OrderIntegralDTO {

    /**
     * 订单消费金额
     */
    private Double amount;

    /**
     * 返回积分数值
     */
    private Integer integral;
}
