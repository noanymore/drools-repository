package me.sdevil507.drools.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 
 * @TableName insurance_question_info
 */
@TableName(value ="insurance_question_info")
@Data
public class InsuranceQuestionInfo implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 评测成员
     */
    private String userId;

    /**
     * 评测人id
     */
    private String mainMemberId;

    /**
     * 家庭关系
     */
    private Integer relation;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 已有保单问卷答案
     */
    private InsuranceQues insuranceQuestion;

    /**
     * 已有疾病问卷答案
     */
    private Pathology pathology;

    /**
     * 家庭总收入
     */
    private Integer familyIncome;

    /**
     * 生日
     */
    private Date birth;

    /**
     * 是否专家咨询
     */
    private Boolean consultNeed;

    /**
     * 软删除
     */
    private Boolean isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}