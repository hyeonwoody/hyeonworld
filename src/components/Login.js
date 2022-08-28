import React, {useState, useEffect} from 'react';
import axios from 'axios';

function Login(){
    const [inputName, setInputName] = useState('')

	const handleInputName = (e) => {
		setInputName(e.target.value)
	}

	const onClickLogin = () => {
		console.log ('click login', inputName)
	}

    // useEffect(()=>{
    //     console.log("ff",window.location.href);
    //     axios.get('/member/login')
    //     .then(res=>console.log(res))
    //     .catch()
    // })V

	return (
        <main>
            <form>
                <h1 className="h3 mb-3 fw-normal">해주세요 로그인.</h1>
                <div className="form-floating">
                    <input className="form-control" id="floatingInput" placeholder='성함' value={inputName} onChange={handleInputName}></input>
                    <label htmlFor="floatingInput">성함</label>
                </div>
                <div className="checkbox mb-3">
                </div>
                <button type="submit" onClick={onClickLogin} className="w-100 btn btn-lg btn-primary">로그인</button>
                <p className="mt-5 mb-3 text-muted">© HyeonwoORld v0.1 2022</p>
            </form>
            {/* <Box name="nope" num="1"/>
            <Gene/>
            <Box name="없어" num="2"/>
            <Gene/>
            <Box name="fvxcv" num="3"/>
            <>{count}</>
            <>state:{count2}</> */}
        </main>
		// < >
		// 	<h1>Login</h1>
		// 	<div>
		// 		<label htmlFor='input_id'>ID : </label>
		// 		<input type='text' name='input_id' value={inputId} onChange={handleInputId} />
		// 	</div>
		// 	<div>
		// 		<button type='button' class='btn btn-primary'>Login</button>
		// 	</div>
		// </>
	)
}

export default Login;