package com.ssafy.api.service;

import com.ssafy.db.entity.ConferenceHistory;

/**
 * 방 관련 비즈니스 로직 처리를 위한 서비스 인터페이스 정의
 */

public interface ConferenceService {
    ConferenceHistory exitConference(String userId, Long ConferenceId); //방 나가기
}
