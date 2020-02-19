package top.chenzicong.highconcurrency.elasticsearchDao;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import top.chenzicong.highconcurrency.model.Commodity;

@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity, String> {

}
