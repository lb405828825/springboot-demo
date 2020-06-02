package com.xh.demo_lb.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/*
 * @Author LiuBo
 * @Description //TODO
 * @Date 2020/6/1
 */
@Component
public class InitListener implements ApplicationListener<ApplicationReadyEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(InitListener.class);

//    @Override
//    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        InetAddress local = null;
//        try {
//            local = Inet4Address.getLocalHost();
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//        String localIP = local.getHostAddress();
//        LOG.info("test flag， lcoal ip:" + localIP);
//    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        InetAddress local = null;
        try {
            local = Inet4Address.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String localIP = local.getHostAddress();
        LOG.info("test flag， lcoal ip:" + localIP);
    }
}
