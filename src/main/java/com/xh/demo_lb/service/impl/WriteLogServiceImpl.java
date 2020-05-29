package com.xh.demo_lb.service.impl;

import com.xh.demo_lb.model.AccessLog;
import com.xh.demo_lb.service.WriteLogService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/25
 */
@Service
public class WriteLogServiceImpl implements WriteLogService {

    private static Logger logger = LoggerFactory.getLogger(WriteLogServiceImpl.class);

    @Resource
    HConnection hConnection;

    @Value("${hbase.col.family_f_:_f_}")
    private String hbaseColFamily_f_;

    private Put createPut(AccessLog accessLog){
        Put put = new Put(Bytes.toBytes(accessLog.getId()));
        byte[] family = Bytes.toBytes("f");
        if(null != accessLog.getXm()){
            put.addColumn(family, Bytes.toBytes("xm"), Bytes.toBytes(accessLog.getXm()));
        }
        if(null != accessLog.getZjhm()){
            put.addColumn(family, Bytes.toBytes("zjhm"), Bytes.toBytes(accessLog.getZjhm()));
        }
        // 'hbase.columns.mapping'=':key,f:xm,f:zjhm,_f_:60b32,_f_:2e040'
        // add new columns:
        // _f_:60b32 ->test_def1, _f_:2e040 ->test_def2
        logger.info("hbaseColFamily_f_:" + hbaseColFamily_f_);
        byte[] family_2 = Bytes.toBytes(hbaseColFamily_f_);
        if(null != accessLog.getTest_def1()){
            put.addColumn(family_2, Bytes.toBytes("60b32"), Bytes.toBytes(accessLog.getTest_def1()));
        }
        if(null != accessLog.getZjhm()){
            put.addColumn(family_2, Bytes.toBytes("2e040"), Bytes.toBytes(accessLog.getTest_def2()));
        }
        return put;
    }

    @Override
    public void insertData(AccessLog accessLog) throws IOException {

        if(null == accessLog){
            return;
        }
        HTable htable = new HTable(TableName.valueOf(Bytes.toBytes("default"),
                Bytes.toBytes("lb.test_hb")), hConnection);
        htable.setAutoFlush(false);
        Put put = createPut(accessLog);
        try {
            htable.put(put);
        } finally {
            htable.close();
        }
        logger.info("Accesslog Inserted : " + accessLog.toString());
    }
}
