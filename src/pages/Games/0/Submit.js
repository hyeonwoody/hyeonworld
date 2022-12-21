import React, { useEffect, useState } from 'react';
import axios from 'axios';

let first = ''
let second = ''
let third = ''
let afalse = 0


function Submit(){

    const [inputFirst, setFirst] = useState ('');
    const [inputSecond, setSecond] = useState ('');
    const [inputThird, setThird] = useState ('');
    const [inputFalse, setFalse] = useState();
    const [buttonTitle, setbutton] = useState('제출');
    const name = sessionStorage.getItem('memberName')
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
    })
    // 
    const confirmSubmit = () => {
        first =inputFirst
        second = inputSecond
        third = inputThird
        afalse = inputFalse
        let data ={
            NAME: name,
            FIRST: inputFirst,
            SECOND: inputSecond,
            THIRD: inputThird,
            AFLASE: inputFalse
        }
        axios.post ('/tmp/submit/game0/set', null, {
            params: data
        })
        if (window.confirm(inputFalse+"번째 명제가 거짓인가요?")) {
          axios.post('/tmp/submit/0', null, {
            params: {
                NAME: name,
            }
        })
            .then (res => {
                console.log("결과 " , res.data.resultcode);
                if (res.data.resultcode){
                    sessionStorage.Item ("memberName")
    
                }
            })
            .catch (err => console.log (err))
        }
        else {
            console.log("cancel")
        }
    };
    useEffect(() => {
        axios.post ('/tmp/submit/game0/get', null, {
                params: {
                    NAME: name
                }
            })
                .then (async (res)=>{
                    console.log("리절트 보기 : ",res.data)
                    setFirst(res.data.FIRST);
                    setSecond(res.data.SECOND);
                    setThird(res.data.THIRD);
                    setFalse(res.data.FALSE);
                    if (res.data.RESULT_CODE === 0){
                        setbutton('다시 제출')
                    }
                    
                })
    },[])
    return (
        <main >
      <form><div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={afalse !==0? first : inputFirst} onChange={handleInputFirst}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>첫번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener" id="1" onClick={onClickRadio}/>
  </div><div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={afalse !==0? second : inputSecond} onChange={handleInputSecond}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>두번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener"  id="2" onClick={onClickRadio}/>
  </div>
  <div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={afalse !==0? third : inputThird} onChange={handleInputThird}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>세번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener"  id="3" onClick={onClickRadio} />
  </div>
  <button type="submit" onClick={confirmSubmit} className="w-100 btn btn-lg btn-primary">{buttonTitle}</button></form>
    
    </main>
    )
}
export default Submit;