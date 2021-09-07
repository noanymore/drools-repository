package me.sdevil507.drools.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author songqc
 * @className InsuranceQues
 * @description 保险问卷参保情况
 * @date 2021/7/9 10:11
 */
@Data
public class InsuranceQues implements Serializable {

  private static final long serialVersionUID = 1187864915485155119L;
  /**
   * 社会保险
   */
  private Boolean socialInsurance;
  /**
   * 惠民保
   */
  private Boolean huiMinInsurance;
  /**
   * 医疗险  0.无 1.百万医疗2.住院门诊3.防癌医疗
   */
  private Integer medicalInsurance;
  /**
   * 重疾险
   */
  private Integer majorDiseaseInsurance;
  /**
   * 意外险
   */
  private Boolean accidentInsurance;
}
