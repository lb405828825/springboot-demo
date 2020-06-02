package com.xh.demo_lb.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvatarResponse {
//    String zpbm;
    String type;
    String sfzhm;
    String xm;
//    String ly;
//    String cjfs;
//    LocalDateTime yxqs;
//    LocalDateTime yxqz;
    String img;

    public AvatarResponse() {
    }

    public AvatarResponse(String img) {
        this();
        this.img = img;
    }

}
