var React = require('react');
import antd from 'antd'; 
var reqwest = require('reqwest');
var Checkbox = antd.Checkbox ; 
 
var checkboxData = [];
reqwest({
            url: '/modules/manage/system/dict/cache/list.htm',
            method: 'get', 
            type: 'json',
            data: {
              type:'NUMBER_RULES'
            },  
            success:(result) => {  
              checkboxData = result.NUMBER_RULES;

            }
      });
export default React.createClass({ 
    getInitialState() {
        return {  
          checkboxList:this.props.checkboxList?this.props.checkboxList:[]
        };
    },
    changeJudge(number,e) { 
      var checkboxList= this.state.checkboxList;
        if(e.target.checked){
          checkboxList.push(number);
        }
        else checkboxList.splice(checkboxList.indexOf(number),1);
        var sum =0;
        checkboxList.forEach((v,i)=>{
          var num = checkboxData[v].text.replace(/[^0-9]/ig,"");
          sum += parseInt(num); 
        });
        checkboxList.sort();
      this.setState({checkboxList:checkboxList}); 
      this.props.checkboxListChange(checkboxList,sum);
    }, 
    componentWillReceiveProps(nextProps) {  
       this.setState({checkboxList:nextProps.checkboxList});
    },
    render() { 
        var state =this.state; 
        var checkboxList = checkboxData.map((item,i)=>{
                var checked = false;
                if(state.checkboxList.indexOf(i)>-1){
                  checked = true;
                } 
                return <label className='col-12'  key={i}><Checkbox name={item.value} onChange={this.changeJudge.bind(this, i)} checked={checked}/>{item.text}</label>
               
              }); 
       
        return (  
              <div className="col-8">
                {checkboxList}
              </div> 
        );
    }
});
 
