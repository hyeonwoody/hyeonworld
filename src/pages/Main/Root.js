import React, { useEffect,useState } from "react";


import Home from '../Home/Home'
import Login from '../Login/Login'
import './Root.css'
function  Root (){
    const [isLogin, setIsLogin] = useState(false);
    
    useEffect(()=>{
        if (sessionStorage.getItem('memberName')===null){
            console.log("Main.js : isLogin?", )
        }
        else {
            setIsLogin(true)
        }
    }, [])
    
    return (
        <div className="Root">
            {isLogin?
            <Home isLogin={isLogin}/>:
            <Login/>}
        </div>
    )
}

export default Root;