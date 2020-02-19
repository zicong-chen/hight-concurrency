package top.chenzicong.highconcurrency.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "commodity")
public class Commodity {

    @Id
    private String skuId;

    private String name;

    private String category;

    private Integer price;

    private String brand;

    private Integer stock;


}
