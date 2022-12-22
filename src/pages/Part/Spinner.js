import React, { useEffect, useState }  from "react";
import './Spinner.css'
function Spinner(props) {
    const [count, changeCount] = useState(0);
    function print(){
        changeCount(count+1)
        console.log(count)
        if (count%7 === 0){
            return <p>다른 가족 들을 기다리고 있습니다</p>
        }
        if (count%7 === 1){
            return <p>다른 가족 들을 기다리고 있습니다.</p>
        }
        if (count%7 === 2){
            return <p>다른 가족 들을 기다리고 있습니다..</p>
        }
        if (count%7 === 3){
            return <p>다른 가족 들을 기다리고 있습니다...</p>
        }
        if (count%7 === 4){
            return <p>다른 가족 들을 기다리고 있습니다..</p>
        }
        if (count%7 === 5){
            return <p>다른 가족 들을 기다리고 있습니다.</p>
        }
        if (count%7 === 6){
            return <p>다른 가족 들을 기다리고 있습니다</p>
        }
    }
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