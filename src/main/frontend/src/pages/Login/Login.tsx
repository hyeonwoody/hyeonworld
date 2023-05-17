import React, {useState} from 'react';
import LoginAxios from './LoginAPI';
import Copyright from "../../parts/copyright/Copyright";

function Login (){
    const [inputName, setInputName] = useState('');

    const handleInputName = (event : React.ChangeEvent<HTMLInputElement>) =>{
        setInputName(event.target.value)
    }

    const onClickLogin = (event : React.MouseEvent<HTMLButtonElement>) => {
        console.log ("FFF");
        event.preventDefault();
        function callback(data : number) {
            console.log("dsdsdsd"+data);

            document.location.href = '/';
        }

        LoginAxios ('/member/login-confirm', inputName, callback);
        // fetch('/onLogin')
        //     .then((res) => res.json())
        //     .then (data=>{setData(data); console.log("THIS : ",data.last)}, ()=>{console.log ("THAT : ",data)})
        //     .catch(err => console.log("fcc",err))
        //postLogin()


    }

    return (
        <div className="Login">
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">
                Hello, Tailwind CSS!
            </button>
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-green-500 hover:bg-blue-700" type="submit" onClick={onClickLogin}>로그인</button>
            <form>
                <h1  className="h3 mb-3 fw-normal" >해주세요 로그인.</h1>
                <div className="form-floating">
                    <input  className="form-control" id="floatingInput" placeholder='성함' value={inputName} onChange={handleInputName}></input>
                    <label htmlFor="floatingInput" style={{'color' : "#181717", "textAlign": "center"}}>성함</label>
                </div>
                <div className="checkbox mb-3"></div>

                <div className="checkbox mb-3"></div>
                <Copyright/>
            </form>
        </div>
    );
}
export default Login;