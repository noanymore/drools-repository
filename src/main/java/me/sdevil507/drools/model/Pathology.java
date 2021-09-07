package me.sdevil507.drools.model;

import lombok.Data;

/**
 * @author songqc
 * @className Pathology
 * @description 保险问卷疾病种类
 * @date 2021/7/9 10:11
 */
@Data
public class Pathology {
        /** 高血压 */
        private Boolean hypertension;
        /** 糖尿病 */
        private Boolean diabetes;
        /** 甲状腺结节 */
        private Boolean ThyroidNodules;
        /** 其他慢性病 */
        private Boolean chronic;
}
