package com.xlhj.search.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xlhj.search.entity.SysUser;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.search.SearchHits;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lcj
 * @since 2020-10-01
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 从ES中获取数据
     * @param index
     * @param documentId
     * @return
     */
    SysUser getElasticsearchData(String index, String documentId);

    /**
     * 将数据保存进ES
     * @param index
     * @param userList
     */
    DocWriteResponse.Result indexElasticsearchData(String index, List<SysUser> userList);

    /**
     * 判断ES中是否存在信息
     * @param index
     * @param documentId
     * @return
     */
    boolean existElasticsearchData(String index, String documentId);

    /**
     * 更新ES中数据信息
     * @param index
     * @param user
     */
    DocWriteResponse.Result updateElasticsearchData(String index, SysUser user);

    /**
     * 根据documentId删除ES中的数据
     * @param index
     * @param documentId
     */
    DocWriteResponse.Result deleteElasticsearchData(String index, String documentId);

    /**
     * 查询ES中所有数据
     * @return
     */
    SearchHits searchElasticAllData();

    /**
     * 根据关键字查询ES
     * @param index
     * @param keyword
     * @return
     */
    SearchHits searchElasticKeyWordData(String index, String keyword);
}
