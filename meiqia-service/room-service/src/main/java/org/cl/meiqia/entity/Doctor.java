package org.cl.meiqia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author yiyu
 * @since 2020-12-09
 */
@TableName("t_doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "user_id", type = IdType.AUTO)
    private Long userId;

    /**
     * 身份证号
     */
    @TableField("card")
    private String card;

    /**
     * 资格
     */
    @TableField("type_id")
    private Long typeId;

    /**
     * 擅长
     */
    @TableField("adept")
    private String adept;

    /**
     * 工龄
     */
    @TableField("seniority")
    private Integer seniority;

    /**
     * 诊室
     */
    @TableField("room_id")
    private Long roomId;

    @TableField(exist = false)
    private String bname;

    @TableField(exist = false)
    private String cname;

    @TableField(exist = false)
    private BigDecimal cprice;

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public BigDecimal getCprice() {
        return cprice;
    }

    public void setCprice(BigDecimal cprice) {
        this.cprice = cprice;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }
    public String getAdept() {
        return adept;
    }

    public void setAdept(String adept) {
        this.adept = adept;
    }
    public Integer getSeniority() {
        return seniority;
    }

    public void setSeniority(Integer seniority) {
        this.seniority = seniority;
    }
    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "Doctor{" +
            "userId=" + userId +
            ", card=" + card +
            ", typeId=" + typeId +
            ", adept=" + adept +
            ", seniority=" + seniority +
            ", roomId=" + roomId +
        "}";
    }
}
