package com.xindaibao.cashloan.cl.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "cl_mohe_info_match")
public class ClMoheInfoMatch implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 数据获取任务id (关联)
     */
    @Column(name = "task_id")
    private String taskId;

    /**
     * 姓名是否与运营商数据匹配
     */
    @Column(name = "real_name_check_yys")
    private String realNameCheckYys;

    /**
     * 身份证号码是否与运营商数据匹配
     */
    @Column(name = "identity_code_check_yys")
    private String identityCodeCheckYys;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取数据获取任务id (关联)
     *
     * @return task_id - 数据获取任务id (关联)
     */
    public String getTaskId() {
        return taskId;
    }

    /**
     * 设置数据获取任务id (关联)
     *
     * @param taskId 数据获取任务id (关联)
     */
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    /**
     * 获取姓名是否与运营商数据匹配
     *
     * @return real_name_check_yys - 姓名是否与运营商数据匹配
     */
    public String getRealNameCheckYys() {
        return realNameCheckYys;
    }

    /**
     * 设置姓名是否与运营商数据匹配
     *
     * @param realNameCheckYys 姓名是否与运营商数据匹配
     */
    public void setRealNameCheckYys(String realNameCheckYys) {
        this.realNameCheckYys = realNameCheckYys;
    }

    /**
     * 获取身份证号码是否与运营商数据匹配
     *
     * @return identity_code_check_yys - 身份证号码是否与运营商数据匹配
     */
    public String getIdentityCodeCheckYys() {
        return identityCodeCheckYys;
    }

    /**
     * 设置身份证号码是否与运营商数据匹配
     *
     * @param identityCodeCheckYys 身份证号码是否与运营商数据匹配
     */
    public void setIdentityCodeCheckYys(String identityCodeCheckYys) {
        this.identityCodeCheckYys = identityCodeCheckYys;
    }
}