import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import axios from "axios";
function Buttons() {
    
    const [isLogin, setIsLogin] = useState (true)
    const navigate = useNavigate();
   
    const onBack = () =>{
        console.log("간다")
        navigate ('/0')
    }
    const onLogout = () => {
        const name = sessionStorage.getItem('memberName')

        axios.post('/onLog/out', null, {
            params: {
                NAME: name,
            }
        })
                    .then (res => {
                        console.log("결과 " , res.data.RESULT_CODE);
                        if (res.data.RESULT_CODE){
                            sessionStorage.removeItem ("memberName")
                            sessionStorage.removeItem("special")
                            setIsLogin (false)
                            document.location.href = '/'
                        }
                        
                    })
                    .catch (err => console.log (err))
    }
    return (
            <div>
            <button type="button" onClick={onBack} className="btn btn-primary"  >다시 제출하기</button>
            
            </div>
    )
}

export default Buttons;