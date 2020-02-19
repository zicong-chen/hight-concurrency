package top.chenzicong.highconcurrency.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.chenzicong.highconcurrency.validator.IsMobile;

import javax.validation.constraints.NotNull;

@Data
public class LoginVo {
    @IsMobile
    @NotNull
    private String mobile;
    @NotNull
    @Length(min = 32)
    private String password;

}
