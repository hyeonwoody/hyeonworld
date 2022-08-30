import React, {useState, useEffect} from 'react';
import axios from 'axios'


function Login(){  



    const [inputName, setInputName] = useState('')

    const handleInputName = (e) =>{
        setInputName(e.target.value)
    }

    const onClickLogin = (e) => {
        e.preventDefault()
        async function postLogin (){
            try {
                await axios.post('/member/onLogin', null,{
                    params: {
                        name : inputName
                    }
                })
                .then(function(res){
                    console.log("result code : ",res.data.resultcode)
                    if (res.data.resultcode ){
                        if (res.data.resultcode === 1)
                            sessionStorage.setItem('memberName', inputName)
                        else if (res.data.resultcode === 2) //admin
                            sessionStorage.setItem('memberName', "2")
                        else if (res.data.resultcode === 3)
                            sessionStorage.setItem('memberName', "3")
                    }
                    else {
                        alert('다시 시도해 주세요.')
                    }
                    document.location.href = '/'
                })
                
                    // sessionStorage.setItem('memberName', inputName)
                .catch();
            }
            catch (e){
                console.error(e);
            }
        }
        
        postLogin()
        // auth.login(inputName).then((res)=>{
        //     console.log(res);
        //     if (res.resultcode){
        //         console.log(res.resultcode)
        //         sessionStorage.setItem('memberName', true)
        //     }
            
        // })
    }

     useEffect(() => {
        // axios.get('/login')
        // .then(res => console.log("xsx",res))
        // .catch()
     },
     [])

    return(
        <main>
        {/* <div><h1 className="title" style={{'--duration': 1}}>
            <span style={{'--delay': .5}}>안녕하세요</span>
            <span style={{'--delay': .8}}>안녕하세A</span>
            </h1></div>
        <div/> */}
        <form>
            <h1  className="h3 mb-3 fw-normal" >해주세요 로그인.</h1>
            <div className="form-floating">
                <input  className="form-control" id="floatingInput" placeholder='성함' value={inputName} onChange={handleInputName}></input>
                <label for="floatingInput" style={{'color' : "#181717"}}>성함</label>
            </div>
            <div className="checkbox mb-3"></div>
            <button i type="submit" onClick={onClickLogin} className="w-100 btn btn-lg btn-primary" style={{'font-family': "recipe"}} >로그인</button>
            <div className="checkbox mb-3"></div>
            <p  className="text" style={{'color' : "#a5a9aa", "font-size": "12px"}}>© HyeonwoORld v0.1 2022</p>
        </form>
        </main>
    )
}

export default Login;
