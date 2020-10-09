package com.xlhj.search.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xlhj.search.config.ElasticsearchConfig;
import com.xlhj.search.entity.SysUser;
import com.xlhj.search.mapper.SysUserMapper;
import com.xlhj.search.service.SysUserService;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lcj
 * @since 2020-10-01
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Override
    public SysUser getElasticsearchData(String index, String documentId) {
        GetRequest request = new GetRequest(index, documentId);
        SysUser user = null;
        try {
            GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            if (response.isExists()) {
                long version = response.getVersion();
                String sourceStr = response.getSourceAsString();
                user = JSON.parseObject(sourceStr, SysUser.class);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public DocWriteResponse.Result indexElasticsearchData(String index, List<SysUser> userList) {
        IndexRequest request = new IndexRequest(index);
        DocWriteResponse.Result result = null;
        try {
            for (SysUser user : userList) {
                request.id(String.valueOf(user.getId()));
                String esStr = JSON.toJSONString(user);
                request.
                request.source(esStr, XContentType.JSON);
                IndexResponse response = restHighLevelClient.index(request, ElasticsearchConfig.COMMON_OPTIONS);
                result = response.getResult();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean existElasticsearchData(String index, String documentId) {
        GetRequest request = new GetRequest(index, documentId);
        boolean exists = false;
        try {
            request.fetchSourceContext(new FetchSourceContext(false));
            request.storedFields("_none_");
            exists = restHighLevelClient.exists(request, ElasticsearchConfig.COMMON_OPTIONS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return exists;
    }

    @Override
    public DocWriteResponse.Result updateElasticsearchData(String index, SysUser user) {
        UpdateRequest request = new UpdateRequest(index, String.valueOf(user.getId()));
        DocWriteResponse.Result result = null;
        try {
            String jsonStr = JSON.toJSONString(user);
            request.doc(jsonStr, XContentType.JSON);
            //request.upsert(jsonStr, XContentType.JSON);//如果ES中有就更新，没有就插入
            UpdateResponse response = restHighLevelClient.update(request, ElasticsearchConfig.COMMON_OPTIONS);
            result = response.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public DocWriteResponse.Result deleteElasticsearchData(String index, String documentId) {
        DeleteRequest request = new DeleteRequest(index, documentId);
        DocWriteResponse.Result result = null;
        try {
            DeleteResponse response = restHighLevelClient.delete(request, ElasticsearchConfig.COMMON_OPTIONS);
            result = response.getResult();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public SearchHits searchElasticAllData() {
        SearchRequest request = new SearchRequest();
        request.indices("sysuser");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchHits hits = null;
        try {
            sourceBuilder.query(QueryBuilders.matchAllQuery());
            request.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(request, ElasticsearchConfig.COMMON_OPTIONS);
            hits = response.getHits();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hits;
    }

    @Override
    public SearchHits searchElasticKeyWordData(String index, String keyword) {
        SearchRequest request = new SearchRequest();
        request.indices(index);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        SearchHits hits = null;
        try {
            sourceBuilder.query(QueryBuilders.matchQuery("loginName", keyword));
            request.source(sourceBuilder);
            SearchResponse response = restHighLevelClient.search(request, ElasticsearchConfig.COMMON_OPTIONS);
            hits = response.getHits();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return hits;
    }
}
