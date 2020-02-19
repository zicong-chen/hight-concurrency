package top.chenzicong.highconcurrency.validator;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import top.chenzicong.highconcurrency.utils.ValidatorUtil;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Slf4j
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {

    private boolean required = false;

    public void initialize(IsMobile constraintAnnotation) {
        //默认是需要的
        required = constraintAnnotation.required();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        System.out.println("woaini");
    }
}
