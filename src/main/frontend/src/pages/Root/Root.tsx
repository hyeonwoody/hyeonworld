import React, {useCallback, useEffect, useState} from 'react';
import Home from '../Home/Home';
import Login from '../Login/Login';


function Root(){

    const [isLogin, setIsLogin] = useState(false);
    const [memberName, setMemberName] = useState("");

    useEffect(()=>{
        // if (false){
        //
        // }
        // else {
        //     setIsLogin(true);
        // }
    },[])
    const handleLogin = useCallback ((data : boolean, loginName :string)=>{
        console.log("뉴뉴");
        setIsLogin(data);
        setMemberName(loginName);
    },[isLogin]);
    return (
        <div className="Root">
            {isLogin? <Home rootCall={handleLogin} name={memberName}/>:
                        <Login rootCall={handleLogin}/>}

            </div>
    );
}

export default Root;