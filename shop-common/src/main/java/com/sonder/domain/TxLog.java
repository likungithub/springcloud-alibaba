package com.sonder.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

//消息事务状态记录
@Entity(name = "tx_log")
@Data
public class TxLog {

    @Id
    private String txId;

    private Date date;

}
