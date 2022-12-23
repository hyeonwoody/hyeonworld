import axios from "axios";
import React, { useEffect } from "react";
import { useQuery } from "react-query";
var obj =[]



function Check0() {
    
    axios.post ('/stage/gameCheck')
    .then ((res) => {
        console.log ("Admin GAME ",res.data.CURRENT_GAME)
        console.log ("Admin STAGE",res.data.CURRENT_STAGE)
    });

    const {data, status} = useQuery('getCorrectMember',  ()=>{
        axios.post ('/result/0/get', null,{

        })
        //.then ((res)=> res.json())
        .then ((res)=> {
            obj = (res.data)
            console.log ("이거는 show",obj)
            console.log ("이거는 showaaaa",obj[0])
            
        })
    },{
        enabled: true,
        refetchInterval: 50000,
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

    
    return (
            <main>
                {/* <p>{(obj.FIRST)}</p>
                <p>{(obj.SECOND)}</p>
                <p>{(obj.THIRD)}</p> */}
                <h2>정답 맞추신 분</h2>

                {obj.map((name, i)=>{
                    
                    return <p>{name}</p>
                })}
            </main>
    )
}

export default Check0;