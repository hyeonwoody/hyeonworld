import axios from 'axios';
import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';

import Admin from "../Main/Admin/Admin";

function Submit() {
  const [DB, setDB] = useState([])  
  
  const [inputFirst, setFirst] = useState ('')
  const [inputSecond, setSecond] = useState ('')
  const [inputThird, setThird] = useState ('')
  const [inputFalse, setFalse] = useState('1')

  const handleInputFirst= (e) =>{
    setFirst(e.target.value)
}
const handleInputSecond= (e) =>{
  setSecond(e.target.value)
}
const handleInputThird= (e) =>{
  setThird(e.target.value)
}

const onClickRadio = ((e) =>{
  setFalse(parseInt(e.target.getAttribute("id")))
  console.log (inputFalse )
})
  const confirmSubmit = () => {
    const namE = sessionStorage.getItem('memberName')
    if (window.confirm(inputFalse+"번째 명제가 거짓인가요?")) {
      axios.post('/member/onSubmit0', null, {
        params: {
            name: namE,
            first: inputFirst,
            second: inputSecond,
            third: inputThird,
            iFalse: inputFalse
        }
    })
                .then (res => {
                    console.log("결과 " , res.data.resultcode);
                    if (res.data.resultcode){
                        sessionStorage.Item ("memberName")
                        document.location.href = '/'
                    }
                    
                })
                .catch (err => console.log (err))
    } else {
      console.log("cancel")
    }
  };

useEffect(() => {
  
},
[DB])
  return (
 <main >
      <form><div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={inputFirst} onChange={handleInputFirst}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>첫번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener" id="1" onClick={onClickRadio} checked="checked"/>
  </div><div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={inputSecond} onChange={handleInputSecond}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>두번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener"  id="2" onClick={onClickRadio}/>
  </div>
  <div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={inputThird} onChange={handleInputThird}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>세번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener"  id="3" onClick={onClickRadio} />
  </div>
  <button type="submit" onClick={confirmSubmit} className="w-100 btn btn-lg btn-primary">제출</button></form>
    </main>
  );
}



export default Submit;
