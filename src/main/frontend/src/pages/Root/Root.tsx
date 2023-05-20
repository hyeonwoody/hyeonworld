import React, {useCallback, useEffect, useState} from 'react';
import Home from '../Home/Home';
import Login from '../Login/Login';


function Root(){

    const [isLogin, setIsLogin] = useState(true);
    const [memberName, setMemberName] = useState("");

    useEffect(()=>{
        // if (false){
        //
        // }
        // else {
        //     setIsLogin(true);
        // }
    },[]);
    const handleLogin = useCallback ((data : boolean, loginName :string)=>{
        setIsLogin(data);
        setMemberName(loginName);
    },[isLogin]);

    return (
        <div className="Root">
            <div className="h-screen from-sky-100 via-sky-300 to-blue-200 bg-gradient-to-br">
                {isLogin? <Home rootCall={handleLogin} name={memberName}/>:
                            <Login rootCall={handleLogin}/>}
            </div>
        </div>
    );
}


export default Root;