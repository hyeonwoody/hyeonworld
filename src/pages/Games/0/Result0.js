import axios from "axios";
import React, { useEffect, useState } from "react";

var obj =[]



function Check0() {
    const [waitingText, setText] = useState("두구두구")
 
    

    useEffect(()=>{



        axios.post ('/result/0/set/whos', null, {

        })

        axios.post ('/result/0/get', null,{

        })
        //.then ((res)=> res.json())
        .then ((res)=> {
            obj = (res.data)
            console.log ("이거는 show",obj)
            (obj.length === 0? setText("없습니다") : setText("축하합니다"))
            
        })
    },[]);

    
    return (
            <main>
                {/* <p>{(obj.FIRST)}</p>
                <p>{(obj.SECOND)}</p>
                <p>{(obj.THIRD)}</p> */}
                <h2>정답 맞추신 분</h2>
                
                {obj.length === 0? <p>{waitingText}</p>:(obj.map((name, i)=>{
                    
                    return <p>{name}</p>
                }))}
            </main>
    )
}

export default Check0;