var OrdinaryUserList = require('../../AgentManage/OrdinaryUserList/index');//普通用户列表-管理员
var AgentList = require('../../AgentManage/AgentList/index');//代理商列表-管理员
var AgentModuleAdmin = require('../../AgentManage/AgentModuleAdmin/index');//代理商管理-管理员
var ShareDetail = require('../../AgentManage/ShareDetail/index');//分润明细-管理员
var CashCheck = require('../../AgentManage/CashCheck/index');//提现查看-管理员
var CashRecord = require('../../AgentManage/CashRecord/index');//提现记录-管理员
var AgentModuleAdminBranch = require('../../AgentBranchManage/AgentModuleAdminBranch/index');//代理商管理-代理商
var ShareDetailBranch = require('../../AgentBranchManage/ShareDetailBranch/index');//分润明细-代理商
var CashCheckBranch = require('../../AgentBranchManage/CashCheckBranch/index');//提现查看-代理商
var CashRecordBranch = require('../../AgentBranchManage/CashRecordBranch/index');//提现记录-代理商
var remitList = require('../../moneyManage/remitList/index');//打款列表
var remitListNoExport = require('../../moneyManage/remitListNoExport/index');//打款列表(无导出)
var OperatingChargesList = require('../../moneyManage/OperatingChargesList/index');//运营费支付记录
var remitCheck = require('../../moneyManage/remitCheck/index');//打款审核
var systemCallList = require('../../moneyManage/systemCallList/index');//系统打款列表
var DailyPassRate = require('../../WindControlData/DailyPassRate/index');//每日通过率
var PlatformDataDaily = require('../../WindControlData/PlatformDataDaily/index');//平台数据日报
var DailyRepaymentAnalysis = require('../../OperationalData/DailyRepaymentAnalysis/index');//每日还款分析
var MonthlyRepaymentAnalysis = require('../../OperationalData/MonthlyRepaymentAnalysis/index');//每月还款分析
var Monthly = require('../../OperationalData/Monthly/index');//每月逾期分析
var IncomeStatistics = require('../../StatisticalManagement/IncomeStatistics/index');//收入统计列表
var ExpenditureStatistics = require('../../StatisticalManagement/ExpenditureStatistics/index');//支出统计列表
var DailyPrincipal = require('../../StatisticalManagement/DailyPrincipal/index');//每日未还本金列表
var DailylLoanData = require('../../StatisticalManagement/DailylLoanData/index');//每日放款收支数据
var TimedTaskList = require('../../TimedTaskList/index');//定时任务
var TimedTaskLog = require('../../TimedTaskLog/index');//定时任务
var LoanInformation = require('../../UserLoanManage/LoanInformation/index');//借款列表
var LoanInformationOnlySearch = require('../../UserLoanManage/LoanInformationOnlySearch/index');//借款列表(仅查询)
var NoteMessage = require('../../NewsRecord/NoteMessage/index');//消息管理
var NoteMould = require('../../NewsRecord/NoteMould/index');//消息模块
// var FormulaCategoryMng = require('../../RepaymentAllocation/FormulaCategoryMng/index');//公式类别管理
// var FormulaVariableMng = require('../../RepaymentAllocation/FormulaVariableMng/index');//公式变量管理
// var FormulaConfiguration = require('../../RepaymentAllocation/FormulaConfiguration/index');//公式配置
// var RepaymentModeAllocation = require('../../RepaymentAllocation/RepaymentModeAllocation/index');//还款方式配置
// var ProductMng = require('../../RepaymentAllocation/ProductMng/index');//产品管理
// var PreviewRepaymentPlan = require('../../RepaymentAllocation/PreviewRepaymentPlan/index');//还款计划预览
var DownloadLog = require('../../DownloadLog/index');//系统管理-下载日志

module.exports = {
  OrdinaryUserList,
  AgentList,
  AgentModuleAdmin,
  ShareDetail,
  CashCheck,
  CashRecord,
  AgentModuleAdminBranch,
  ShareDetailBranch,
  CashCheckBranch,
  CashRecordBranch,
  remitList,
  remitListNoExport,
  OperatingChargesList,
  remitCheck,
  systemCallList,
  DailyPassRate,
  PlatformDataDaily,
  DailyRepaymentAnalysis,
  MonthlyRepaymentAnalysis,
  Monthly,
  IncomeStatistics,
  ExpenditureStatistics,
  DailyPrincipal,
  DailylLoanData,
  TimedTaskList,
  TimedTaskLog,
  LoanInformation,
  LoanInformationOnlySearch,
  NoteMessage,
  NoteMould,
  DownloadLog
}