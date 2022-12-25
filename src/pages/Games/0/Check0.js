import axios from "axios";
import React, {useEffect, useState} from "react";
import { useQuery } from "react-query";

var obj = []
function Check0() {
  const addShow = (e) => {
    console.log("누굴까",e.target.id)
    // fetch('/onLogin')
    let a = document.getElementById(e.target.id+"1");
    a.ariaExpanded = false
    console.log(a)
    axios.post ('/show/0/set', null,{
      params:{
        WHO: e.target.id,
    }
    })
    axios.post ('/result/0/init', null,{
      
    })
  }

    const {data, status} = useQuery('getGame0Submits',  ()=>{
        fetch('/check/0',{
            method: "POST", //GET , POST, PUT, DELETE
        })
            .then ((res)=> res.json())
            .then ((data)=> {
                obj = []
                data.forEach(element => {
                    var a = JSON.parse(element)
                        obj.push (a)
                });
            })
    },{
        enabled: true,
        cacheTime: Infinity,
    });

    console.log("데이터")
    if (status === 'loading'){
        console.log("로딩중")
    }
    if (status === 'error'){
        console.log ('에러 발생')
    }
    if (status === 'success'){
        console.log("성공")


    }

    useEffect(()=>{
        
    },[]);

    return (
            <main>

              


<div class="accordion accordion-flush" id="accordionFlushExample">
  
</div>
            {obj.map((item, i)=>{

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
                })}
  
  


            </main>
    )
}

export default Check0;