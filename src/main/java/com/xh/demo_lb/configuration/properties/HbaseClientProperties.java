package com.xh.demo_lb.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/25
 */
@Setter
@Getter
@Component("hbase client properties info")
@ConfigurationProperties(prefix = "hbaseconf")
public class HbaseClientProperties {
    private String hbaseZkQuorum;
    private String zkZnodeParent;
    private String hbaseZkPropertyClientPort;
    private int hbaseRpcTimeout;
    private int hbaseClientRetriesNumber;
}
