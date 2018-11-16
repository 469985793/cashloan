var FormEvent = {
  handleOk() {
    var me = this;
    var validation = this.refs.validation,
      status = 'create';
    validation.validate((valid) => {
      if (!valid) {
        //console.log('error in form');
        return;
      } else {
        var serviceVariables = {}, processStateCode;
        var selectRecord = this.props.selectRecord;
        serviceVariables.processInstanceId = selectRecord.processInstanceId;
        serviceVariables.projectId = selectRecord.projectId;
        serviceVariables.consultid = selectRecord.consultid;
        serviceVariables.carGpsAssembly = this.state.formData;
        processStateCode = selectRecord.processStateCode;
        reqwest({
          url: '/modules/common/action/ServiceFormDraftSaverAction/saveFormDraft.htm'
          , method: 'post'
          , data: {
            formName: processStateCode,
            serviceVariables: JSON.stringify(serviceVariables)
          }
          , type: 'json'
          , success: (result) => {
            if (result.code == 200) {
              Modal.success({
                title: result.msg
              });
            }
            else if (result.code == 400) {
              Modal.error({
                title: result.msg
              });
            }
          }
        });
      }
    });
    this.refs.validation.reset();
  }
};
module.exports = FormEvent;