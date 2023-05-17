import React,{useEffect, useState} from 'react';
import Home from '../Home/Home';
import Login from '../Login/Login';


function Root(){

    const [isLogin, setIsLogin] = useState(false);

    useEffect(()=>{
        // if (false){
        //
        // }
        // else {
        //     setIsLogin(true);
        // }
    },[])

    return (
        <div className="Root">
            {isLogin? <Home isLogin={isLogin}/>:
                        <Login/>}

            </div>
    );
}

export default Root;