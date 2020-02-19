package top.chenzicong.highconcurrency.model;

import lombok.Data;

@Data
public class OrderInfo {
    private Long id;
    private Long userId;
    private Long goodsId;
    private Long deliveryAdrId;
    private String goodsName;
    private Integer goodsCount;
    private Double orderChannel;
    private Integer status;
    private Data createDate;
    private Data payDate;

}
