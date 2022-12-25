import axios from "axios";
import React, { useEffect } from "react";


var ranking =[]
var reason 
var correct = []
var missing = []
function Ranking0() {
    
    useEffect(()=>{
        axios.post ('/ranking/0', null,{
            
        })
            .then ((res)=> {
                
                ranking = res.data
                //actual.clear()
                for (var z=0; z<ranking.length; z++){
                    reason = ranking[z].scoreReason.split('^')
                    let who = ""
                    let count =""
                    
                    for (var a=1; a<reason.length; a++){
                        var parse = reason[a].split('_')
                        console.log("리즌즌", parse)      
                            //parse[0] == Game0
                            //parse[1] == type
                            //parse[2] == person
                            if (parse[1][0] === 'F'){ //from
                                who += parse[2]+", "
                            }
                            else if (parse[1][0] === 'M') { //missing
                                count += parse[2]
                            }
                            
                            console.log("aaa", who)
                    }
                    who = who.substring (0, who.length-2)
                    correct.push (who)
                    missing.push (count)
                }
            })
    },[]);

    return (
            <main>
                <h2>진실 혹은 거짓</h2>
                {/* {ranking.map((item,i)=>{
                    return <p>{item.name}</p>
                })} */}

                {ranking.map((item, i)=>{

                    return (<div className="accordion-item">
                    <h2 className="accordion-header collapsed" id="panelsStayOpen-headingOne">
                      <button id={item.name}key="fdd" className="btn btn-lg btn-primary" type="button" data-bs-toggle="collapse" data-bs-target={"#panelsStayOpen-"+String(i)} aria-controls={"#panelsStayOpen-"+String(i)}>
                        {i+1}등 {item.name} {item.game00Score}점
                      </button>
                    </h2>
                    <div id={"panelsStayOpen-"+String(i)} key="aa" className="accordion-collapse collapse collapsed" aria-labelledby={"#panelsStayOpen-heading"+String(i)} data-bs-parent="#accordionFlushExample">      
                      <div className="accordion-body">
                      <p>{correct[i]}</p>
                      <p>{missing[i]}</p>
                      </div>

                    </div>
                  </div>
                )
                })} 



{/* {obj.map((item, i)=>{

return (<div class="accordion-item">
<h2 class="accordion-header collapsed" id="panelsStayOpen-headingOne">
  <button id={item.NAME+"1"}key="fdd" class="btn btn-lg btn-primary" type="button" data-bs-toggle="collapse" data-bs-target={"#panelsStayOpen-"+String(i)} aria-controls={"#panelsStayOpen-"+String(i)}>
    {item.NAME}
  </button>
</h2>
<div id={"panelsStayOpen-"+String(i)} class="accordion-collapse collapse collapsed" aria-labelledby={"#panelsStayOpen-heading"+String(i)} data-bs-parent="#accordionFlushExample">
  <div class="accordion-body">
  {item.AFALSE === "1"? (<p style={{color:"red"}}>{item.FIRST}</p>):(<p>{item.FIRST}</p>)}
  {item.AFALSE === "2"? (<p style={{color:"red"}}>{item.SECOND}</p>):(<p>{item.SECOND}</p>)}
  {item.AFALSE === "3"? (<p style={{color:"red"}}>{item.THIRD}</p>):(<p>{item.THIRD}</p>)}
 
  </div>
  <button class="btn btn-lg btn-primary" id={item.NAME}onClick={addShow}/>
</div>
</div>
)
})} */}
            </main>
    )
};

export default Ranking0;