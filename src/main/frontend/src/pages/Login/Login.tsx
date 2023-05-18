import React, {useCallback, useState} from 'react';
import LoginAxios from './LoginAPI';
import Copyright from "../../parts/copyright/Copyright";

// @ts-ignore
import styles from './Login.css';

interface LoginProps{
    rootCall: (data: boolean, userName: string) => void;
}

function Login (props : LoginProps){
    const [inputName, setInputName] = useState('');

    const handleInputName = (event : React.ChangeEvent<HTMLInputElement>) =>{
        setInputName(event.target.value)
    }

    const onClickLogin = (event : React.MouseEvent<HTMLButtonElement>) => {
        console.log ("FFF");
        event.preventDefault();
        function checkSuccess(success : boolean) {
            console.log("dsdsdsd"+success);

            if (success)
               console.log("성공");
            else {
                console.log("실패");
            }
            props.rootCall(success, inputName);
            // document.location.href = '/';
        }

        LoginAxios ("login-confirm", inputName, checkSuccess);
        // fetch('/onLogin')
        //     .then((res) => res.json())
        //     .then (data=>{setData(data); console.log("THIS : ",data.last)}, ()=>{console.log ("THAT : ",data)})
        //     .catch(err => console.log("fcc",err))
        //postLogin()


    }

    return (
        <body>
            <div className={styles.container}>
                <div className="h-screen bg-sky-600 p-12">
                    <ul className="p-5 space-y-40"/>
                    <div className="rounded-lg bg-blue-200 p-10">
                        <div className="grid grid-cols-1">
                            <input  className="form-control text-1xl p-4" id="floatingInput" placeholder='성함을 입력해주세요' value={inputName} onChange={handleInputName}></input>
                            <ul className="p-5 space-y-10"/>
                            <button className="py-2 px-4 rounded-3xl shadow-md text-white bg-blue-500 hover:bg-blue-700" type="submit" onClick={onClickLogin}>로그인</button>
                        </div>
                        <Copyright/>
                    </div>
                </div>
            </div>
        </body>

    );
}
export default Login;