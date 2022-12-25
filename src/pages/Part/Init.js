import React, {useEffect, useState} from "react";
import {useNavigate} from 'react-router-dom';
import axios from "axios";
import { useQuery } from "react-query";

import './Buttons.css'
//import {socket} from '../Games/Games'

function Init() {
    
    const [isLogin, setIsLogin] = useState (true)
    const navigate = useNavigate();
    const special = sessionStorage.getItem('special')
        const {data, status} = useQuery('Log',  ()=>{
            if (special == '1')
            axios.post('/onLog/force', null, {
            })
                .then (res => {
                console.log("결과sssss " , res.data.RESULT_CODE);
                if (res.data.RESULT_CODE){
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
                
                })
        },{
            enabled: (special == '1'),
            cacheTime: Infinity,
            refetchInterval: 2000
        });
    



    const onBack = () =>{
        navigate ('/')
    }
    const onLogout = () => {
        const name = sessionStorage.getItem('memberName')
        console.log ("이름은 ", name)
        

    
    }
    return (
            <div>
            </div>
    )
}

export default Init;