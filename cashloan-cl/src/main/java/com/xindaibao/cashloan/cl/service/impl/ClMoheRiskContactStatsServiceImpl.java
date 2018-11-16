package com.xindaibao.cashloan.cl.service.impl;

import com.xindaibao.cashloan.cl.domain.*;
import com.xindaibao.cashloan.cl.mapper.*;
import com.xindaibao.cashloan.cl.service.ClMoheRiskContactStatsService;
import com.xindaibao.cashloan.core.common.mapper.BaseMapper;
import com.xindaibao.cashloan.core.common.service.impl.BaseServiceImpl;
import com.xindaibao.cashloan.core.common.util.BeanUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static com.xindaibao.cashloan.cl.service.impl.ClMoheRiskContactStatsServiceImpl.RiskContactStatsTypeEnum.*;

@Slf4j
@Service("clMoheRiskContactStatsStatsService")
public class ClMoheRiskContactStatsServiceImpl
        extends BaseServiceImpl<ClMoheRiskContactStats, Long> implements ClMoheRiskContactStatsService {

    @Autowired private ClMoheRiskContactStatsMapper clMoheRiskContactStatsMapper;

    @Override
    public BaseMapper<ClMoheRiskContactStats, Long> getMapper() {
        return clMoheRiskContactStatsMapper;
    }


    @Override
    public void saveStatsByRiskType(ClMoheData clMoheData) {
        log.info("Parse and save risk_contact_stats begin.");
        List<ClMoheRiskContactStats> riskContactStatsList = clMoheData.getRisk_contact_stats();
        if (CollectionUtils.isEmpty(riskContactStatsList)) {
            log.info("No RiskContactStats data...");
            return;
        }
        for (ClMoheRiskContactStats one : riskContactStatsList) {
            String riskType = one.getRiskType();
            try {
                if (StringUtils.equals(RISK_TYPE_110.getValue(), riskType)) {
                    ClMoheRiskContact110Stats stats = new ClMoheRiskContact110Stats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContact110StatsMapper.save(stats);
                }
                if (StringUtils.equals(RISK_TYPE_120.getValue(), riskType)) {
                    ClMoheRiskContact120Stats stats = new ClMoheRiskContact120Stats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContact120StatsMapper.save(stats);
                }
                if (StringUtils.equals(RISK_TYPE_MACAO.getValue(), riskType)) {
                    ClMoheRiskContactMacaoStats stats = new ClMoheRiskContactMacaoStats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContactMacaoStatsMapper.save(stats);
                }

                if (StringUtils.equals(RISK_TYPE_DEBT_COLLECT.getValue(), riskType)) {
                    ClMoheRiskContactDebtCollectStats stats = new ClMoheRiskContactDebtCollectStats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContactDebtCollectStatsMapper.save(stats);
                }
                if (StringUtils.equals(RISK_TYPE_LAWYER.getValue(), riskType)
                        || StringUtils.equals("律师事务所", riskType)) {

                    ClMoheRiskContactLawyerStats stats = new ClMoheRiskContactLawyerStats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContactLawyerStatsMapper.save(stats);
                }
                if (StringUtils.equals(RISK_TYPE_SMALL_LOAN.getValue(), riskType)
                        || StringUtils.equals("小贷公司", riskType)) {

                    ClMoheRiskContactSmallLoanStats stats = new ClMoheRiskContactSmallLoanStats();
                    BeanUtils.copyProperties(stats, one);

                    clMoheRiskContactSmallLoanStatsMapper.save(stats);
                }
            } catch (Exception e) {
                log.error("Copy obj and save error.", e);
            }
        }

        log.info("Parse and save risk_contact_stats end.");
    }

    @Autowired private ClMoheRiskContact110StatsMapper clMoheRiskContact110StatsMapper;
    @Autowired private ClMoheRiskContact120StatsMapper clMoheRiskContact120StatsMapper;
    @Autowired private ClMoheRiskContactMacaoStatsMapper clMoheRiskContactMacaoStatsMapper;
    @Autowired private ClMoheRiskContactDebtCollectStatsMapper clMoheRiskContactDebtCollectStatsMapper;
    @Autowired private ClMoheRiskContactLawyerStatsMapper clMoheRiskContactLawyerStatsMapper;
    @Autowired private ClMoheRiskContactSmallLoanStatsMapper clMoheRiskContactSmallLoanStatsMapper;

    /**
     * 110、120、澳门电话、律师号码、催收、小贷公司
     */
    @AllArgsConstructor
    @Getter
    enum RiskContactStatsTypeEnum {
        RISK_TYPE_110("110"),
        RISK_TYPE_120("120"),
        RISK_TYPE_MACAO("澳门电话"),
        RISK_TYPE_LAWYER("律师号码"),
        RISK_TYPE_DEBT_COLLECT("催收"),
        RISK_TYPE_SMALL_LOAN("小贷"),

        RISK_TYPE_OTHER("其他"); //非正常

        private String value;

        public static RiskContactStatsTypeEnum matchValue(String value) {
            for (RiskContactStatsTypeEnum typeEnum : RiskContactStatsTypeEnum.values()) {
                if (StringUtils.equals(typeEnum.getValue(), value)) {
                    return typeEnum;
                }
            }
            return RISK_TYPE_OTHER;
        }
    }
}
