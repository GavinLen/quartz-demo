package xyz.ieden.quartz.simple.ram.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author lianghongwei01
 * @date 2018/8/20 17:23
 */
@Setter
@Getter
@ToString
public class JobDetailInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * Job 描述
     */
    private String description;
    /**
     * Job 名称
     */
    private String name;
    /**
     * Job 组名
     */
    private String group;
}
