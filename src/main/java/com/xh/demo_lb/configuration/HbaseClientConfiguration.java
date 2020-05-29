package com.xh.demo_lb.configuration;

import com.xh.demo_lb.configuration.properties.HbaseClientProperties;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HConnectionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.IOException;

@Configuration
public class HbaseClientConfiguration {

    @Resource
    private HbaseClientProperties hbaseClientProperties;

    @Bean
    public HConnection hConnection() throws Exception {
        org.apache.hadoop.conf.Configuration configuration = this.hConfigration();
        HConnection hConnection = HConnectionManager.getConnection(configuration);
        System.out.printf("hConnection:" + hConnection);
        return hConnection;
    }

    @Bean
    public org.apache.hadoop.conf.Configuration hConfigration() throws IOException {
        org.apache.hadoop.conf.Configuration configuration = new org.apache.hadoop.conf.Configuration();
        configuration.set("hbase.zookeeper.quorum", hbaseClientProperties.getHbaseZkQuorum());
        configuration.set("zookeeper.znode.parent", hbaseClientProperties.getZkZnodeParent());
        configuration.set("hbase.zookeeper.property.clientPort", hbaseClientProperties.getHbaseZkPropertyClientPort());
        configuration.set("hbase.client.retries.number", String.valueOf(hbaseClientProperties.getHbaseClientRetriesNumber()));
        configuration.set("hbase.rpc.timeout", String.valueOf(hbaseClientProperties.getHbaseRpcTimeout()));
//        configuration.set("hbase.zookeeper.quorum", hbaseClientProperties.getHbaseZkQuorum());
//        configuration.set("zookeeper.znode.parent", hbaseClientProperties.getZkZnodeParent());
//        configuration.set("hbase.zookeeper.property.clientPort", hbaseClientProperties.getHbaseZkPropertyClientPort());
//        configuration.set("hbase.client.retries.number", String.valueOf(hbaseClientProperties.getHbaseClientRetriesNumber()));
//        configuration.set("hbase.rpc.timeout", String.valueOf(hbaseClientProperties.getHbaseRpcTimeout()));

//        if (!StringUtils.isEmpty(hbaseClientProperties.getAuthentication())) {
//            // security
//            configuration.set("hbase.master.kerberos.principal", hbaseClientProperties.getPrincipal());
//            configuration.set("hbase.regionserver.kerberos.principal", hbaseClientProperties.getPrincipal());
//            configuration.set("hbase.security.authentication", hbaseClientProperties.getAuthentication());
//            configuration.set("hadoop.security.authentication", hbaseClientProperties.getAuthentication());
//
//            UserGroupInformation.setConfiguration(configuration);
//            UserGroupInformation.loginUserFromKeytab(hbaseClientProperties.getLoginUser(), hbaseClientProperties.getKeytabFile());
//        }
        return configuration;
    }
}
