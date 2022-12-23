import React, { useEffect, useState }  from "react";
import './Spinner.css'
function Spinner(props) {
    const [count, changeCount] = useState(0);
    
    useEffect(()=>{

    },[])
    return (

  <main>
    <div className="spinner-border text-primary" role="status">
        <span className="visually-hidden">Spinner</span>
    </div>
    <div>다른 가족들을 기다리고 있습니다.</div>
    </main>
            
        
    )
}

export default Spinner;