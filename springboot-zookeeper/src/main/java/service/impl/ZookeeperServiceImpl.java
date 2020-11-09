package service.impl;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import service.ZookeeperService;

import javax.annotation.PostConstruct;

/**
 * @ClassName ZookeeperServiceImpl
 * @Description zookeeper业务接口实现类
 * @Author liucaijing
 * @Date 2020/11/9 22:56
 * @Version 1.0
 */
@Service
public class ZookeeperServiceImpl implements ZookeeperService {

    private final static Logger logger = LoggerFactory.getLogger(ZookeeperServiceImpl.class);
    private CuratorFramework client = null;
    @Value("${zookeeper.server}")
    private String server;

    private String namespace;

    private String digest;

    @Override
    @PostConstruct
    public void init() {

    }

    @Override
    public void createNode(CreateMode mode, String path, String nodeData) {

    }
}
