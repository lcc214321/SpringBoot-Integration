package com.xlhj.xxljob.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.util.ShardingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @Author: lcj
 * @Date: 2020/11/3 8:55
 * @Description: 定时任务处理类
 * @Version: 0.0.1
 */
@Component
public class XxlJobHandler {

    private Logger logger = LoggerFactory.getLogger(XxlJobHandler.class);

    /**
     * 简单任务(Bean模式)
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("jobHandler")
    public ReturnT<String> jobHandler(String param) throws Exception {
        logger.info("XXL-JOB, Hello World");
        for (int i = 0; i < 5; i++) {
            logger.info("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 分片广播任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("shardingJobHandler")
    public ReturnT<String> shardingJobHandler(String param) throws Exception {
        ShardingUtil.ShardingVO shardingVO = ShardingUtil.getShardingVo();
        logger.info("分片参数：当前分片序号 = {},总分片数 = {}", shardingVO.getIndex(), shardingVO.getTotal());
        for (int i = 0; i < shardingVO.getTotal(); i++) {
            if (i == shardingVO.getIndex()) {
                logger.info("第{}片，命中分片开始处理!", i);
            } else {
                logger.info("第{}片，忽略!", i);
            }
        }
        return ReturnT.SUCCESS;
    }

    /**
     * 命令行执行
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("commandJobHandler")
    public ReturnT<String> commandJobHandler(String param) throws Exception {
        String command = param;
        int exitValue = -1;
        BufferedReader bufferedReader = null;
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(process.getInputStream());
            bufferedReader = new BufferedReader(new InputStreamReader(bufferedInputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                logger.info(line);
            }
            process.waitFor();
            exitValue = process.exitValue();
        } catch (Exception e) {
            logger.info("出现异常!", e);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
        if (exitValue == 0) {
            return IJobHandler.SUCCESS;
        } else {
            return new ReturnT<String>(IJobHandler.FAIL.getCode(), "command exit value(" + exitValue + ") is failed");
        }
    }

    /**
     * 跨平台Http任务
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob("httpJobHandler")
    public ReturnT<String> httpJobHandler(String param) throws Exception {
        if (param == null || param.trim().length() == 0) {
            logger.info("param[" + param + "] invalid.");
            return ReturnT.FAIL;
        }
        String[] httpParams = param.split("\n");
        String url = null;
        String method = null;
        String data = null;
        for (String httpParam : httpParams) {
            if (httpParam.startsWith("url:")) {
                url = httpParam.substring(httpParam.indexOf("url:") + 4).trim();
            }
            if (httpParam.startsWith("method:")) {
                method = httpParam.substring(httpParam.indexOf("method:") + 7).trim().toUpperCase();
            }
            if (httpParam.startsWith("data:")) {
                data = httpParam.substring(httpParam.indexOf("data:") + 5).trim();
            }
        }
        if (url == null || url.trim().length() == 0) {
            logger.info("url[" + url + "] invalid.");
            return ReturnT.FAIL;
        }
        if (method == null || !Arrays.asList("GET", "POST").contains(method)) {
            logger.info("method[" + method + "] invalid.");
            return ReturnT.FAIL;
        }
        HttpURLConnection connection = null;
        BufferedReader bufferedReader = null;
        try {
            URL realUrl = new URL(url);
            connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod(method);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setReadTimeout(5 * 1000);
            connection.setConnectTimeout(3 * 1000);
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            connection.setRequestProperty("Accept-Charset", "application/json;charset=UTF-8");
            connection.connect();
            if (data != null && data.trim().length() > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
                dataOutputStream.write(data.getBytes("UTF-8"));
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            int statusCode = connection.getResponseCode();
            if (statusCode != 200) {
                throw new RuntimeException("Http Request StatusCode(" + statusCode + ") Invalid.");
            }
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }
            String responseMsg = result.toString();
            logger.info(responseMsg);
            return ReturnT.SUCCESS;
        } catch (Exception e) {
            logger.info("执行异常!", e);
            return ReturnT.FAIL;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (connection != null) {
                    connection.disconnect();
                }
            } catch (Exception E) {
                logger.info("执行异常!", E);
            }
        }
    }

    /**
     * 生命周期任务示例：任务初始化与销毁时，支持自定义相关逻辑
     * @param param
     * @return
     * @throws Exception
     */
    @XxlJob(value = "jobHandler2", init = "init", destroy = "destroy")
    public ReturnT<String> jobHandler2(String param) throws Exception {
        logger.info("XXL-JOB, Hello World.");
        return ReturnT.SUCCESS;
    }

    public void init() {
        logger.info("init");
    }

    public void destroy() {
        logger.info("destory");
    }
}
