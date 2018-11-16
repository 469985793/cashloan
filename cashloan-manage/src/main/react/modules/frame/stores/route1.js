var CheckManagement = require('../../CheckManagement/index');//对账管理
var CheckManagementNoExport = require('../../CheckManagementNoExport/index');//对账管理
var ChannelInformationStatistics = require('../../ChannelInformationStatistics/index');//渠道信息统计
var ChannelAppManage = require('../../ChannelAppManage/index');//渠道app管理
var ChannelManage = require('../../ChannelManage/index');//渠道管理
var ScenePortManage = require('../../ScenePortManage/index');//场景与接口关系维护
var ThirdPartyInquiry = require('../../ThirdPartyInquiry/index');//第三方征信 
var RiskControlDataStatistics = require('../../RiskControlDataStatistics/index');//风控数据统计
var RulesMatchResults = require('../../RulesMatchResults/index');//规则匹配结果
var FormFieldsToAdd = require('../../FormFieldsToAdd/index');//表字段维护 
var CollectionPersonnelList = require('../../CollectionManage/CollectionPersonnelManage/CollectionPersonnelList/index');//催收人员管理-催收人员列表
var CollectionFeedback = require('../../CollectionManage/CollectionPersonnelManage/CollectionFeedback/index');//催收人员管理-催收反馈意见
var CollectionFeedbackNoExport = require('../../CollectionManage/CollectionPersonnelManage/CollectionFeedbackNoExport/index');//催收人员管理-催收反馈意见
var CollectionTotalOrderList = require('../../CollectionManage/CollectionOrderListManage/CollectionTotalOrderList/index');//催收订单列表管理-催收总订单列表
var CollectionTotalOrderListNoExport = require('../../CollectionManage/CollectionOrderListManage/CollectionTotalOrderListNoExport/index');//催收订单列表管理-催收总订单列表
var CollectionRecordList = require('../../CollectionManage/CollectionOrderListManage/CollectionRecordList/index');//催收订单列表管理-待催收记录列表
var MyOrder = require('../../CollectionManage/MyCollectionOrderManage/MyOrder/index');//我的催收订单管理-我的订单
var CollectionFeedbackManage = require('../../CollectionManage/MyCollectionOrderManage/CollectionFeedbackManage/index');//我的催收订单管理-催收反馈
var WaitCollectionList = require('../../CollectionManage/MyCollectionOrderManage/WaitCollectionList/index');//我的催收订单管理-待催收列表
var CollectionInList = require('../../CollectionManage/MyCollectionOrderManage/CollectionInList/index');//我的催收订单管理-催收中列表
var CommitmentRepaymentList = require('../../CollectionManage/MyCollectionOrderManage/CommitmentRepaymentList/index');//我的催收订单管理-承诺还款列表
var CollectionSuccessList = require('../../CollectionManage/MyCollectionOrderManage/CollectionSuccessList/index');//我的催收订单管理-催收成功列表
var RecoveryRateStatistics = require('../../CollectionManage/CollectionStatisticsManage/RecoveryRateStatistics/index');//催收统计管理-催回率统计
var CollectionOrderStatistics = require('../../CollectionManage/CollectionStatisticsManage/CollectionOrderStatistics/index');//催收统计管理-催收订单统计
var CollectionDailyStatistics = require('../../CollectionManage/CollectionStatisticsManage/CollectionDailyStatistics/index');//催收统计管理-催收员每日统计
var OverdueNoAdmission = require('../../CollectionManage/CollectionWarning/OverdueNoAdmission/index');//催收预警-已逾期未入催
var NoRepaymentPutForward = require('../../CollectionManage/CollectionWarning/NoRepaymentPutForward/index');//催收预警-未还款已出催
var UnallocatedCollection = require('../../CollectionManage/CollectionWarning/UnallocatedCollection/index');//催收预警-未分配催收员
var StandbyReviewList = require('../../RiskControlManage/StandbyReviewList/index');//待机审订单列表
var MachinePassList = require('../../RiskControlManage/MachinePassList/index') //机审通过列表
var RejectedOrderList = require('../../RiskControlManage/RejectedOrderList/index') //机审拒绝订单列表
var ShildCreditAuditRecords = require('../../RiskControlManage/ShildCreditAuditRecords/index'); //同盾贷前审核记录
var ShildCreditAuditRecordsNoExport = require('../../RiskControlManage/ShildCreditAuditRecordsNoExport/index'); //同盾贷前审核记录
var LoanApplicationManage = require('../../RiskControlManage/LoanApplicationManage/index'); //人工复审
var MachineRequestRecord = require('../../RiskControlManage/MachineRequestRecord/index'); //机审请求列表
var AdverMangeList = require('../../AdverMangeList/index'); //广告信息
var ChannelInformationStatisticsCPS = require('../../ChannelInformationStatisticsCPS/index');//渠道信息统计GSP
var ChannelInformationStatisticsCPA = require('../../ChannelInformationStatisticsCPA/index');//渠道信息统计GAP

var WorkbenchIndex = require('../../Public/Workbench/Index');//平台数据统计

module.exports = {
  CheckManagement,
  CheckManagementNoExport,
  ChannelInformationStatistics,
  ChannelAppManage,
  ChannelManage,
  ScenePortManage,
  ThirdPartyInquiry,
  RiskControlDataStatistics,
  RulesMatchResults,
  FormFieldsToAdd,
  CollectionPersonnelList,
  CollectionFeedback,
  CollectionFeedbackNoExport,
  CollectionTotalOrderList,
  CollectionTotalOrderListNoExport,
  CollectionRecordList,
  MyOrder,
  CollectionFeedbackManage,
  WaitCollectionList,
  CollectionInList,
  CommitmentRepaymentList,
  CollectionSuccessList,
  RecoveryRateStatistics,
  CollectionOrderStatistics,
  CollectionDailyStatistics,
  OverdueNoAdmission,
  NoRepaymentPutForward,
  UnallocatedCollection,
  StandbyReviewList,
  MachinePassList,
  RejectedOrderList,
  ShildCreditAuditRecords,
  ShildCreditAuditRecordsNoExport,
  LoanApplicationManage,
  MachineRequestRecord,
  AdverMangeList,
  ChannelInformationStatisticsCPS,
  ChannelInformationStatisticsCPA,
  WorkbenchIndex
}