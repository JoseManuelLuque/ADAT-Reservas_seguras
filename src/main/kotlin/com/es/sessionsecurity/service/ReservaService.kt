package com.es.sessionsecurity.service

import com.es.sessionsecurity.repository.ReservaRepository
import com.es.sessionsecurity.repository.SessionRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ReservaService {
    @Autowired
    private lateinit var reservaRepository: ReservaRepository

    @Autowired
    private lateinit var sessionRepository: SessionRepository


}