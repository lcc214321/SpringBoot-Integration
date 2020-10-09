package com.xlhj.search.controller;


import com.alibaba.fastjson.JSON;
import com.xlhj.search.common.AjaxResult;
import com.xlhj.search.config.ElasticsearchConfig;
import com.xlhj.search.entity.SysUser;
import com.xlhj.search.service.SysUserService;
import io.swagger.annotations.ApiOperation;
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
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lcj
 * @since 2020-10-01
 */
@RestController
@RequestMapping("/search/user")
public class SysUserController {

    @Autowired
    private SysUserService userService;

    @PutMapping("/indexElasticsearchData")
    @ApiOperation(value = "将用户信息保存进es中")
    public AjaxResult indexElasticsearchData() throws IOException {
        List<SysUser> userList = userService.list(null);
        DocWriteResponse.Result result = userService.indexElasticsearchData("sysuser", userList);
        return AjaxResult.ok().data("result", result);
    }

    @GetMapping("/searchElasticAllData")
    @ApiOperation(value = "查询ES中所有数据")
    public AjaxResult searchElasticAllData() throws IOException {
        SearchHits hits = userService.searchElasticAllData();
        List<SysUser> userList = getSearchResult(hits);
        return AjaxResult.ok().data("userList", userList);
    }

    private List<SysUser> getSearchResult(SearchHits hits) {
        SearchHit[] searchHits = hits.getHits();
        List<SysUser> userList = new ArrayList<SysUser>();
        for (SearchHit hit : searchHits) {
            String sourceStr = hit.getSourceAsString();
            System.out.println(sourceStr);
            SysUser user = JSON.parseObject(sourceStr, SysUser.class);
            userList.add(user);
        }
        return userList;
    }

    @GetMapping("/searchElasticKeyWordData/{keyword}")
    @ApiOperation(value = "根据关键字查询ES中数据")
    public AjaxResult searchElasticKeyWordData(@PathVariable String keyword) throws IOException {
        SearchHits hits = userService.searchElasticKeyWordData("sysuser", keyword);
        List<SysUser> userList = getSearchResult(hits);
        return AjaxResult.ok().data("userList", userList);
    }

    /**
     * 根据ID查询ES中用户信息
     * @param id
     * @throws IOException
     */
    @GetMapping("/getElasticsearchData/{id}")
    @ApiOperation(value = "根据ID查询es中用户信息")
    public AjaxResult getElasticsearchData(@PathVariable Long id) throws IOException {
        SysUser user = userService.getElasticsearchData("sysuser", String.valueOf(id));
        return AjaxResult.ok().data("user", user);
    }

    /**
     * 根据ID查询ES中是否存在用户信息
     * @param id
     * @throws IOException
     */
    @GetMapping("/existElasticsearchData/{id}")
    @ApiOperation(value = "根据ID查询es中是否存在用户信息")
    public AjaxResult existElasticsearchData(@PathVariable Long id) throws IOException {
        boolean exist = userService.existElasticsearchData("sysuser", String.valueOf(id));
        return AjaxResult.ok().data("exist", exist);
    }

    /**
     * 更新ES中数据信息
     * @param user
     * @throws IOException
     */
    @PutMapping("/updateElasticsearchData")
    @ApiOperation(value = "根据ID更新es中用户信息")
    public AjaxResult updateElasticsearchData(@RequestBody SysUser user) throws IOException {
        DocWriteResponse.Result result = userService.updateElasticsearchData("sysuser", user);
        return AjaxResult.ok().data("result", result);
    }

    /**
     * 根据ID删除ES中数据
     * @param id
     * @return
     * @throws IOException
     */
    @GetMapping("/deleteElasticsearchData/{id}")
    @ApiOperation(value = "根据ID删除es中用户信息")
    public AjaxResult deleteElasticsearchData(@PathVariable Long id) throws IOException {
        DocWriteResponse.Result result = userService.deleteElasticsearchData("sysuser", String.valueOf(id));
        return AjaxResult.ok().data("result", result);
    }
}

