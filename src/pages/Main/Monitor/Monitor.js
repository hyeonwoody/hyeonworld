import React, {useEffect, useState} from "react";

import axios from "axios";
function Monitor() {
    const [isLogin, setIsLogin] = useState (true)
    const onLogout = () => {
        const name = sessionStorage.getItem('memberName')

        axios.post('/onLog/in', null, {
            params: {
                NAME: name,
            }
        })
                    .then (res => {
                        console.log("결과 " , res.data.resultcode);
                        if (res.data.resultcode){
                            sessionStorage.removeItem ("memberName")
                            sessionStorage.removeItem("special")
                            setIsLogin (false)
                            document.location.href = '/'
                            
                        }
                        
                    })
                    .catch (err => console.log (err))
        
        
    }
    return (
        
        <main>
            <div>fsdㄹㅇㄹfdsf</div>

            <button type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button>
        </main>
        
    )
}

export default Monitor;