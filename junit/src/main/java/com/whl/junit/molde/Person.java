package com.whl.junit.molde;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;

@Data
public class Person {

    @NotBlank(message = "不能为空")
    @Length(min = 2, max = 20, message = "长度要在2到20之间")
    private String name;

    @NotNull
    @Min(value = 17, message = "最小值为17")
    private Integer age;

    @NotEmpty
    @Email(message="邮件格式不正确")
    private String email;

    @NotNull(message="手机号必须填")
    @Pattern(regexp = "^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$", message="账号请输入11位手机号") // 手机号
    private String mobile;

    @NotNull(message="昵称必须填")
    @Size(min=1, max=20, message="昵称1~20个字")
    private String nickname;

    @NotNull(message="密码必须填")
    @Size(min=6, max=16, message="密码6~16位")
    private String password;
}
