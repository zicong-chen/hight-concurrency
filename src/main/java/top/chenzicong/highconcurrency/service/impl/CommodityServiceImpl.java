package top.chenzicong.highconcurrency.service.impl;

import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;
import top.chenzicong.highconcurrency.elasticsearchDao.CommodityRepository;
import top.chenzicong.highconcurrency.model.Commodity;
import top.chenzicong.highconcurrency.service.CommodityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    ElasticsearchTemplate elasticsearchTemplate;


    @Override
    public long count() {
        return commodityRepository.count();
    }

    @Override
    public Commodity save(Commodity commodity) {
        return commodityRepository.save(commodity);
    }

    @Override
    public void delete(Commodity commodity) {
        commodityRepository.delete(commodity);
    }

    @Override
    public Iterable<Commodity> getAll() {
        return commodityRepository.findAll();
    }

    @Override
    public List<Commodity> getByName(String name) {

        List<Commodity> commodityList = new ArrayList<>();
        MatchQueryBuilder matchQueryBuilder = new MatchQueryBuilder("name", name);
        Iterable<Commodity> commodities = commodityRepository.search(matchQueryBuilder);
        commodities.forEach(commodityList::add);
        return commodityList;
    }

    @Override
    public Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        return commodityRepository.search(searchQuery);
    }
}
