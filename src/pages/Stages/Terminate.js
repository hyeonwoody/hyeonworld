import React, { useEffect } from "react";
import { useNavigate } from "react-router-dom";


function Terminate(props){
    const navigate = useNavigate()
    useEffect(()=>{
        navigate ('/')
    },[])
    return (
        <p>끝</p>
    )
}
    
    
export default Terminate;