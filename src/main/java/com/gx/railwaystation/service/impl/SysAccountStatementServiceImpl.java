package com.gx.railwaystation.service.impl;

import com.gx.railwaystation.mapper.SysAccountStatementMapper;
import com.gx.railwaystation.mapper.SysMoneyMapper;
import com.gx.railwaystation.po.SysAccountStatement;
import com.gx.railwaystation.po.SysMoney;
import com.gx.railwaystation.service.SysAccountStatementService;
import com.gx.railwaystation.vo.LayuiTableData;
import com.gx.railwaystation.vo.StatementVo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysAccountStatementServiceImpl implements SysAccountStatementService {

    private SysMoneyMapper sysMoneyMapper;

    private SysAccountStatementMapper sysAccountStatementMapper;

    @Autowired
    public SysAccountStatementServiceImpl(SysMoneyMapper sysMoneyMapper, SysAccountStatementMapper sysAccountStatementMapper) {
        this.sysMoneyMapper = sysMoneyMapper;
        this.sysAccountStatementMapper = sysAccountStatementMapper;
    }

    @Override
    public SysMoney selectByUserId(Integer userId) {
        return this.sysMoneyMapper.selectByUserId(userId);
    }

    @Override
    public boolean updateMoney(SysMoney sysMoney) {
        return this.sysMoneyMapper.updateMoney(sysMoney)>0;
    }

    @Override
    public boolean insertMoney(SysMoney money) {
        return this.sysMoneyMapper.insertMoney(money)>0;
    }

    @Override
    public boolean insertAccountStatement(SysAccountStatement accountStatement) {
        return this.sysAccountStatementMapper.insertAccountStatement(accountStatement)>0;
    }

    @Override
    public List<StatementVo> selectStatement(Integer userId) {
        return this.sysAccountStatementMapper.selectStatement(userId);
    }

    @Override
    public LayuiTableData<SysAccountStatement> selectByType(int limit, int page, Integer moneyId, Integer consumerType, Integer limitDays) {
        List<SysAccountStatement> sysAccountStatements = this.sysAccountStatementMapper.selectByType(limit, page, moneyId, consumerType, limitDays);
        Integer countAll = this.sysAccountStatementMapper.countAll(moneyId, consumerType, limitDays);
        return new LayuiTableData<>(countAll,sysAccountStatements);
    }


}
