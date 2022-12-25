import axios from "axios";
import React, {useEffect, useSyncExternalStore} from "react";
import { useQuery } from "react-query";
var obj =[]
function Check0() {
    
    axios.post ('/stage/gameCheck')
    .then ((res) => {
        console.log ("Admin GAME ",res.data.CURRENT_GAME)
        console.log ("Admin STAGE",res.data.CURRENT_STAGE)
    });

    const {data, status} = useQuery('getShowMember',  ()=>{
        axios.post ('/show/0/get', null,{
            params:{

            }
        })
        //.then ((res)=> res.json())
        .then ((res)=> {
            obj = (res.data)
            console.log ("이거는 show",obj)
            console.log ("이거는 show",obj.FIRST)
        })
    },{
        enabled: true,
        refetchInterval: 10000,
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




    
    return (
            <main>
                {/* <p>{(obj.FIRST)}</p>
                <p>{(obj.SECOND)}</p>
                <p>{(obj.THIRD)}</p> */}
                <ul class="list-group">
                    <li class="list-group-item list-group-item-action list-group-item-success"style={{'font-size': "20px"}}>{obj.FIRST}</li>
                    <li class="list-group-item list-group-item-action list-group-item-warning"style={{'font-size': "20px"}}>{obj.SECOND}</li>
                    <li class="list-group-item list-group-item-action list-group-item-danger"style={{'font-size': "20px"}}>{obj.THIRD}</li>
                </ul> 
            </main>
    )
}

export default Check0;