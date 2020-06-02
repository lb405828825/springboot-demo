package com.xh.demo_lb.controller;

import com.google.common.base.Strings;
import com.xh.demo_lb.common.ApiResp;
import com.xh.demo_lb.common.ApiRespUtils;
import com.xh.demo_lb.model.AvatarParam;
import com.xh.demo_lb.model.AvatarResponse;
import com.xh.demo_lb.service.AvatarService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Api("Avatar api")
@RestController
@RequestMapping("/api/v1")
public class AvatarController {

    private static Logger LOG = LoggerFactory.getLogger(AvatarController.class);

    @Resource
    private AvatarService avatarService;

    @GetMapping("/avatar")
    public AvatarResponse getAvatar(@RequestParam("zjhm") String citizenID) {
        return avatarService.getAvatarByCitizenID(citizenID);
    }

    @PostMapping(value = "/photoGet", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResp<AvatarResponse> getAvatarByPost(@RequestBody AvatarParam params) {
    	if (!Strings.isNullOrEmpty(params.getSfzhm()) && !Strings.isNullOrEmpty(params.getXm())) {
        	AvatarResponse avatar;
            try {
//                avatar = avatarService.getAvatarByCitizenID(params.getSfzhm());
                avatar = avatarService.getAvatarByCitizenIDAndXm(params.getSfzhm(), params.getXm());
            } catch (Throwable throwable) {
                LOG.error("Failed to get image from HBase", throwable);
                return ApiRespUtils.response("01",throwable.getMessage(),null);
            }
            if(avatar!=null) {
                return ApiRespUtils.success(avatar);
            }
        }
        return ApiRespUtils.success(null);
    }
}
