package com.xh.demo_lb.service.impl;

import com.xh.demo_lb.configuration.properties.AvatarHbaseClientProperties;
import com.xh.demo_lb.model.AvatarResponse;
import com.xh.demo_lb.service.AvatarService;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HConnection;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.mortbay.log.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

@Service
public class AvatarServiceImpl implements AvatarService {

    private static Logger LOG = LoggerFactory.getLogger(AvatarServiceImpl.class);

//    @Resource
//    private AvatarHbaseClientProperties avatarHbaseClientProperties;

    @Resource
    private HConnection hConnection;

    @Override
    public AvatarResponse getAvatarByCitizenID(String citizenID) {
        LOG.info("Query avatar by citizen ID:{}", citizenID);
        TableName photoTableNameJZZ = TableName.valueOf("default", "photo.jzz_photo_hyperbase");
        TableName photoTableNameSFZ = TableName.valueOf("default", "photo.sfz_photo_hyperbase");
        AvatarResponse resp = new AvatarResponse();
        
        try {
            HTable tableSFZ = new HTable(photoTableNameSFZ, hConnection);

            byte[] rk = Bytes.toBytes(citizenID);
            Result resultSFZ = tableSFZ.get(new Get(rk));
            // 获取某一列的值,具体的可以从desc formatted table中看column mappings
            byte[] valueSFZ = resultSFZ.getValue(Bytes.toBytes("f"), Bytes.toBytes("q1"));
            // value就是最终的base64编码
            if(valueSFZ != null){
                String img = new String(valueSFZ, "UTF-8");
                resp.setImg(img);
                resp.setType("00");
                return resp;
            }else {
            	HTable tableJZZ = new HTable(photoTableNameJZZ, hConnection);
            	Result resultJZZ = tableJZZ.get(new Get(rk));
            	byte[] valueJZZ = resultJZZ.getValue(Bytes.toBytes("f"), Bytes.toBytes("q1"));
            	if(null != valueJZZ) {
            		String img = new String(valueJZZ, "UTF-8");
            		resp.setImg(img);
            		resp.setType("01");
            		return resp;
            	}
            }
        } catch (IOException e) {
            LOG.error("IO Exception", e);
        }
        return null;
    }

    @Override
    public AvatarResponse getAvatarByCitizenIDAndXm(String citizenID, String xm) {
        LOG.info("Query avatar by citizen ID:{}, xm:{}", citizenID, xm);
//        TableName photoTableNameJZZ = TableName.valueOf("default", "photo.jzz_photo_hyperbase");
//        TableName photoTableNameSFZ = TableName.valueOf("default", "photo.sfz_photo_hyperbase");
        TableName photoTableNameJZZ = TableName.valueOf("default", "jzz_photo_hyperbase");
        TableName photoTableNameSFZ = TableName.valueOf("default", "sfz_photo_hyperbase");
        AvatarResponse resp = new AvatarResponse();

        try {
            HTable tableSFZ = new HTable(photoTableNameSFZ, hConnection);

            byte[] rk = Bytes.toBytes(citizenID);
            Result resultSFZ = tableSFZ.get(new Get(rk));
            // 获取某一列的值,具体的可以从desc formatted table中看column mappings
            byte[] valueSFZ = resultSFZ.getValue(Bytes.toBytes("f"), Bytes.toBytes("q1"));
            // 姓名 SQL字段：xm, 身份证的hbase表中对应字段名：55e78
            byte[] valueXm = resultSFZ.getValue(Bytes.toBytes("_f_"), Bytes.toBytes("55e78"));
            String hbaseXM = null;
            if(valueXm != null){
                hbaseXM = new String(valueXm);
                LOG.info("hbase SFZ xm:" + hbaseXM);
            }
            // value就是最终的base64编码
            if(valueSFZ != null && xm.equals(hbaseXM)){
                String img = new String(valueSFZ, "UTF-8");
                resp.setImg(img);
                resp.setType("00");
                resp.setXm(xm);
                resp.setSfzhm(citizenID);
                return resp;
            } else {
                HTable tableJZZ = new HTable(photoTableNameJZZ, hConnection);
                Result resultJZZ = tableJZZ.get(new Get(rk));
                byte[] valueJZZ = resultJZZ.getValue(Bytes.toBytes("f"), Bytes.toBytes("q1"));
                // 姓名 SQL字段：xm, 居住证的hbase表中对应字段名：61598
                byte[] valueJzzXm = resultJZZ.getValue(Bytes.toBytes("_f_"), Bytes.toBytes("61598"));
                String hbaseJzzXM = null;
                if(valueJzzXm != null){
                    hbaseJzzXM = new String(valueJzzXm);
                    LOG.info("hbase Jzz xm:" + hbaseJzzXM);
                }
                if(null != valueJZZ && xm.equals(hbaseJzzXM)) {
                    String img = new String(valueJZZ, "UTF-8");
                    resp.setImg(img);
                    resp.setType("01");
                    resp.setXm(xm);
                    resp.setSfzhm(citizenID);
                    return resp;
                }
            }
        } catch (IOException e) {
            LOG.error("IO Exception", e);
        }
        return null;
    }
}
