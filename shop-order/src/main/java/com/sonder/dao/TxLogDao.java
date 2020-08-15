package com.sonder.dao;

import com.sonder.domain.Order;
import com.sonder.domain.TxLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxLogDao extends JpaRepository<TxLog,String> {
}
