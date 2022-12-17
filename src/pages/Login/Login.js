import React, {useState} from "react";

import axios from "axios";

import './Login.css';

function Login (pros){

    const [inputName, setInputName] = useState('')
    const handleInputName = (e) =>{
        setInputName(e.target.value)
    }

    const onClickLogin = (e) => {
        e.preventDefault()
        // fetch('/onLogin')
        //     .then((res) => res.json())
        //     .then (data=>{setData(data); console.log("THIS : ",data.last)}, ()=>{console.log ("THAT : ",data)})
        //     .catch(err => console.log("fcc",err))
        //postLogin()
        axios.post('/onLog/in', null, {
            params: {
                NAME : inputName
            }
        })
            .then ((res) => {
                console.log ("Login, result code : ",res.data.RESULT_CODE)
                const RESULT_CODE = res.data.RESULT_CODE
                if (RESULT_CODE){
                    sessionStorage.setItem ('memberName', inputName)
                    if (RESULT_CODE === 1)
                        sessionStorage.setItem ('special', 1) //players
                    else if (RESULT_CODE === 2)
                        sessionStorage.setItem ('special', 2) //Admin
                    else if (RESULT_CODE === 3)
                        sessionStorage.setItem ('special', 3) //Monitor
                }
                else {
                    alert('다시 시도해 주세요.')
                }
                document.location.href = '/'
            })

    }
    
    return (
        <main className="App">
            
            <form>
            <h1  className="h3 mb-3 fw-normal" >해주세요 로그인.</h1>
            <div className="form-floating">
                <input  className="form-control" id="floatingInput" placeholder='성함' value={inputName} onChange={handleInputName}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717", "textAlign": "center"}}>성함</label>
            </div>
            <div className="checkbox mb-3"></div>
            <button type="submit" onClick={onClickLogin} className="w-100 btn btn-lg btn-primary"  >로그인</button>
            <div className="checkbox mb-3"></div>
            <p  className="text" style={{'color' : "#a5a9aa" }}>© HyeonwoORld v0.3 2022</p>
        </form>

        </main>
    )
}

export default Login;