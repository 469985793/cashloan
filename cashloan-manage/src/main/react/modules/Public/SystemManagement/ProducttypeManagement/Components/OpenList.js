var React = require('react');
import antd from 'antd'; 
var reqwest = require('reqwest');
var Checkbox = antd.Checkbox ; 

export default React.createClass({ 
    getInitialState() {
        return {  
          changeValue:this.props.changeValue?this.props.changeValue:[],
          openData:[],
        };
    },
    changeValue2(e) { 
      this.props.changeValue(e)
  },
  componentDidMount() { 
      var data={};
      var paramId= this.props.SelectRecord.paramId;
        data.id=paramId;
        reqwest({
                url: '/modules/fel/FelparamAction/conditionsQuery.htm',
                method: 'get', 
                type: 'json',
                data: {
                  json:JSON.stringify(data)
                },  
                success:(result) => {  
                  this.setState({openData : result})
                }
          });
  },
    render() { 
     //console.log(this.props.SelectRecord) 
        var state =this.state; 
        var openList = state.openData.map((item,i)=>{
                 return <div className="ant-form-item openList" key={i}>
                             <label className="col-8"  htmlFor={item.text}>{item.text}：</label>
                             <div className="col-16">
                                <input className="ant-input" type="text" disabled={false} readOnly={false} name={item.text}  onChange={this.changeValue2}/>
                             </div> 
                        </div> 
                 }); 
       
        return (  
              <div className="openListWarp col-24">
                {openList}
              </div> 
        );
    }
});
 
