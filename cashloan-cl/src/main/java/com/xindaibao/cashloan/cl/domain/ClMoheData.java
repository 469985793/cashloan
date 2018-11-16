package com.xindaibao.cashloan.cl.domain;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;


/**
 * JSON convert obj
 */
@Getter
@Setter
public class ClMoheData implements Serializable {
    private String code;
    private String msg;

    private ClMoheReportInfo report_info;

    private ClMoheUserInfo user_info;
    private ClMoheWorkTelDetail work_tel_detail;
    private ClMoheActiveSilenceStats active_silence_stats;
    private ClMoheAllContactStats all_contact_stats;
    private ClMoheBehaviorAnalysis behavior_analysis;

    private ClMoheCallDurationStats2hour call_duration_stats_2hour;
    private ClMoheCarrierConsumptionStats carrier_consumption_stats;

    private ClMoheDataCompleteness data_completeness;
    private ClMoheEmergencyContact1Detail emergency_contact1_detail;
    private ClMoheEmergencyContact2Detail emergency_contact2_detail;
    private ClMoheEmergencyContact3Detail emergency_contact3_detail;
    private ClMoheEmergencyContact4Detail emergency_contact4_detail;
    private ClMoheEmergencyContact5Detail emergency_contact5_detail;

    private ClMoheHomeTelDetail home_tel_detail;
    private ClMoheInfoCheck info_check;
    private ClMoheInfoMatch info_match;
    private ClMoheMobileInfo mobile_info;

    private List<ClMoheAllContactDetail> all_contact_detail;
    private List<ClMoheAllContactStatsPerMonth> all_contact_stats_per_month;
    private List<ClMoheRiskContactDetail> risk_contact_detail;
    private List<ClMoheRiskContactStats> risk_contact_stats;
    private List<ClMoheTravelTrackAnalysisPerCity> travel_track_analysis_per_city;

    private List<ClMoheFinanceContactStats> finance_contact_stats;
    private List<ClMoheFinanceContactDetail> finance_contact_detail;
    private List<ClMoheCarrierConsumptionStatsPerMonth> carrier_consumption_stats_per_month;
    private List<ClMoheContactAreaStatsPerCity> contact_area_stats_per_city;
    private List<ClMoheCallAreaStatsPerCity> call_area_stats_per_city;


}
