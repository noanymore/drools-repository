package me.sdevil507.drools.model;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author songqc
 * @className InsuranceRecommendDTO
 * @description 保险产品信息DTO
 * @date 2021/7/19 14:13
 */
@Data
public class InsuranceRecommendDTO implements Serializable {
        private static final long serialVersionUID = 7364906427427744160L;
        /** 保险产品头图 */
        private String picUrl;
        /** 保险产品详情跳转链接 */
        private String url;
        /** 保险产品名称 */
        private String insuranceName;
        /** 保额 */
        private Integer insuranceFee;
        /** 保险价格 */
        private BigDecimal price;
        /** 保险类型 */
        private Integer insuranceType;
        /** 是否展示推荐文案 */
        private Boolean copyWriting;
        /** 推荐文案  */
        private Integer recommendText;
        /** 是够展示告知文案 */
        private Boolean noticeText;
        /** 是否推荐产品 */
        private Boolean willRecommend;
}