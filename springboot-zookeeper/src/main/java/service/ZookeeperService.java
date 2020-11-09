package service;

import org.apache.zookeeper.CreateMode;

/**
 * @ClassName ZookeeperService
 * @Description zookeeper业务接口
 * @Author liucaijing
 * @Date 2020/11/9 22:54
 * @Version 1.0
 */
public interface ZookeeperService {

    void init();

    void createNode(CreateMode mode, String path, String nodeData);
}
