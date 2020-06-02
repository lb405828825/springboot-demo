package com.xh.demo_lb.service;


import com.xh.demo_lb.model.AvatarResponse;

public interface AvatarService {
	AvatarResponse getAvatarByCitizenID(String citizenID);
	AvatarResponse getAvatarByCitizenIDAndXm(String citizenID, String xm);
}
