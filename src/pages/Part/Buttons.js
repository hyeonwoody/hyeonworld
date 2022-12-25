import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import axios from "axios";

import './Buttons.css'
//import {socket} from '../Games/Games'

function Buttons() {
    
    const [isLogin, setIsLogin] = useState (true)
    const navigate = useNavigate();
   
    const onBack = () =>{
        navigate ('/')
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
            <button className="back" type="button" onClick={onBack} className="btn btn-primary"  >뒤로가기</button>
            <button className="logOut" type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button>
            </div>
    )
}

export default Buttons;