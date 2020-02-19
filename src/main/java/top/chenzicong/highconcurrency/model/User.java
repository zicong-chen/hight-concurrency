package top.chenzicong.highconcurrency.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.chenzicong.highconcurrency.validator.IsMobile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Integer id;
    @IsMobile
    private String name;
    private Integer age;



}

