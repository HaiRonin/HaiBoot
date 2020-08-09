package com.whl.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author whl
 * @since 2020-05-28
 */
@TableName("t_user")
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("user_id")
	private Long userId;
    /**
     * 身份证号码
     */
	@TableField("id_number")
	private String idNumber;
    /**
     * 姓名
     */
	private String name;
    /**
     * 年龄
     */
	private Integer age;
    /**
     * 性别：1-男；2-女
     */
	private Integer gender;
    /**
     * 出生日期
     */
	@TableField("birth_date")
	private Date birthDate;


	public Long getUserId() {
		return userId;
	}

	public User setUserId(Long userId) {
		this.userId = userId;
		return this;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public User setIdNumber(String idNumber) {
		this.idNumber = idNumber;
		return this;
	}

	public String getName() {
		return name;
	}

	public User setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getAge() {
		return age;
	}

	public User setAge(Integer age) {
		this.age = age;
		return this;
	}

	public Integer getGender() {
		return gender;
	}

	public User setGender(Integer gender) {
		this.gender = gender;
		return this;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public User setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
