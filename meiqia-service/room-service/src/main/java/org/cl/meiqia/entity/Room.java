package org.cl.meiqia.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author yiyu
 * @since 2020-12-09
 */
@TableName("t_room")
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 诊室
     */
    @TableField("name")
    private String name;

    @TableField(exist = false)
    private List<Doctor> items;

    public List<Doctor> getItems() {
        return items;
    }

    public void setItems(List<Doctor> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
            "id=" + id +
            ", name=" + name +
        "}";
    }
}
