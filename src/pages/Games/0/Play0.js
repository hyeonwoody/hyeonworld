import axios from "axios";
import React, {useEffect, useState} from "react";
import { useQuery } from "react-query";
var obj =[]
function Play0() {
  const [text, setText] = useState("")
    const {data, status} = useQuery('getPlayMember',  ()=>{
        axios.post ('/show/0/get', null,{

        })
        //.then ((res)=> res.json())
        .then ((res)=> {
            obj = (res.data)
            if (obj.FIRST !== undefined){
              setText(" ")
            }
            else {
              console.log("널")
              setText("")
            }
        })
    },{
        enabled: true,
        refetchInterval: 10000,
        cacheTime: Infinity,
    });
    console.log("상태", status)
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

    const jeChul = (e)=>{
        const name = sessionStorage.getItem('memberName')
        const answer = e.target.getAttribute("id")
        if (name !== obj.NAME){
          axios.post ('/result/0/calculate', null,{
            params:{
                NAME: name,
                ANSWER: answer,
                WHOS: obj.NAME
            }
          })
        }
    }

    
    return (
            <main>
                

            <h2>거짓을 골라주세요</h2>
                <div class="accordion" id="accordionExample" >
  <div class="accordion-item" >
    <h2 class="accordion-header" id="headingOne" >
      <button class="accordion-button collapsed" type="button" disabled={!text} data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="false" aria-controls="collapseOne">
        {obj.FIRST}
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body" >
      <button class="btn btn-lg btn-primary" id={"1"}onClick={jeChul} style={{'font-size': "10px"}}>제출</button>
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingTwo">
      <button class="accordion-button collapsed" type="button" disabled={!text} data-bs-toggle="collapse" data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
        {obj.SECOND}
      </button>
    </h2>
    <div id="collapseTwo" class="accordion-collapse collapse" aria-labelledby="headingTwo" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <button class="btn btn-lg btn-primary" id={"2"}onClick={jeChul} style={{'font-size': "10px"}}>제출</button>
      </div>
    </div>
  </div>
  <div class="accordion-item">
    <h2 class="accordion-header" id="headingThree">
      <button class="accordion-button collapsed" type="button" disabled={!text} data-bs-toggle="collapse" data-bs-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
      {obj.THIRD}
      </button>
    </h2>
    <div id="collapseThree" class="accordion-collapse collapse" aria-labelledby="headingThree" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <button class="btn btn-lg btn-primary" id={"3"}onClick={jeChul} style={{'font-size': "10px"}}>제출</button>
      </div>
    </div>
  </div>
</div>
  

            </main>
    )
}

export default Play0;