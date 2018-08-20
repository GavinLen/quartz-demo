package xyz.ieden.quartz.simple.ram.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.quartz.ScheduleBuilder;

import java.io.Serializable;

/**
 * @author lianghongwei01
 * @date 2018/8/20 17:31
 */
@Setter
@Getter
@ToString
public class TriggerInfo implements Serializable {

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
    /**
     *
     */
    private ScheduleBuilder<?> scheduleBuilder;

}
