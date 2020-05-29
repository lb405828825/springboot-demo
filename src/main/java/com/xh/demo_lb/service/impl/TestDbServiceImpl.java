package com.xh.demo_lb.service.impl;

import com.xh.demo_lb.model.TestHyperbase;
import com.xh.demo_lb.service.TestDbService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hyperbase.datatype.StringHDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/5/23
 */
@Service
public class TestDbServiceImpl implements TestDbService {

    private static Logger logger = LoggerFactory.getLogger(TestDbService.class);

    String testStr1 = "{\"a\": 1,\n" +
            "  \"b\": 2,\n" +
            "  \"c\": {\n" +
            "    \"c1\": \"33\",\n" +
            "    \"c2\": \"44\"\n" +
            "  }}";

    @Resource
    HConnection hConnection;

    @Override
    public String printSelectTest(String id)  throws IOException {
        String result = null;
        String idRowKey=id;

        HTable table = new HTable(TableName.valueOf(Bytes.toBytes("default"),
                Bytes.toBytes("lb.test_hb")), hConnection);
        Get get = new Get(Bytes.toBytes(idRowKey));
        // 1. 获取表数据
        Result ret = table.get(get);
        // 3. 获取某一列的值,具体的可以从desc formatted table中看column mappings
        StringHDataType stringHDataType = new StringHDataType();
        if (null != ret) {
//            result = new TestHyperbase();
            byte[] xm = ret.getValue(Bytes.toBytes("f"),Bytes.toBytes("xm"));
            byte[] zjhm = ret.getValue(Bytes.toBytes("f"),Bytes.toBytes("zjhm"));
            if (null != xm){
                logger.info("testHyperbase, xm: " + stringHDataType.decode(xm));
                logger.info("xm,result2：" + Bytes.toString(xm));
            }
            if (null != zjhm){
                logger.info("testHyperbase, zjhm: " + stringHDataType.decode(zjhm));
                logger.info("zjhm, result2：" + Bytes.toString(zjhm));
            }
            result = "xm ：" + Bytes.toString(xm) + "\n" + "zjhm ：" + Bytes.toString(zjhm);
        } else {
            logger.info("Test,get null");
        }
        return result;
    }
}
