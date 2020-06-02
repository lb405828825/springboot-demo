package com.xh.demo_lb.configuration.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
//@Component("avatarHbaseClientProperties")
//@ConfigurationProperties(prefix = "avatar")
public class AvatarHbaseClientProperties extends HbaseClientProperties{
}
