import React, { useEffect, useState } from 'react';
import axios from 'axios';

let confirm = false


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
        setFalse(e.target.getAttribute("id"))
    })
    // 

    console.log("거짓은 ? : ",inputFalse)

    function confirmSubmit()  {

        //var newCount = count.toString()
        // let data ={
        //     NAME: name,
        //     FIRST: inputFirst,
        //     SECOND: inputSecond,
        //     THIRD: inputThird,
        //     AFLASE: inputFalse
        // }
        axios.post ('/tmp/submit/game0/set', null, {
            params: {
                NAME: name,
                FIRST: inputFirst,
                SECOND: inputSecond,
                THIRD: inputThird,
                AFLASE: inputFalse
            }
        })
        if (inputFalse === undefined){
            alert('거짓 명제를 선택해주세요.')
        }
        else if (window.confirm(inputFalse+"번째 명제가 거짓인가요?")) {
            axios.post('/tmp/submit/game0/confirm', null, {
                params: {
                    NAME: name,
                }
            })
                .then (res => {
                    console.log("결과 " , res.data.resultcode);
                    if (res.data.resultcode){
                        sessionStorage.Item ("클라이언트 : memberName")
        
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
                .then ( (res)=>{

                    //count = res.data.CNT
                    console.log("리절트 ", res.data)
                    confirm = res.data.CONFIRM
                    if (res.data.RESULT_CODE === 0 && confirm){
                        console.log("여길 ", buttonTitle.length)
                        setFirst(res.data.FIRST);
                        setSecond(res.data.SECOND);
                        setThird(res.data.THIRD);
                        if (buttonTitle.length === 2){
                            setbutton("다시 제출")
                        }
                        else if (buttonTitle.length>4)
                        setbutton("또 "+buttonTitle)
                    }
                    else if (!confirm){
                        setFirst(res.data.FIRST);
                        setSecond(res.data.SECOND);
                        setThird(res.data.THIRD);
                        setbutton("거짓 명제 선택 후 제출")
                    }
                    
                })
    },[])
    function confirmCheck(){
        return confirm? <p>제출완료</p>:<p>제출해주세요.</p>
    }
    return (
    <div>
        {confirmCheck()}
        <form>
            <div className="form-floating">
                <input  className="form-control" id="floatingInput" placeholder='성함' value={inputFirst} onChange={handleInputFirst}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '1'? "checked" : false}>첫번째 명제</label>
                <div className="checkbox mb-3"></div>
                <input type="radio" name="gener" id="1" onClick={onClickRadio}/>
            </div>
            <div className="form-floating">
                <input  className="form-control" id="floatingInput" placeholder='성함' value={inputSecond} onChange={handleInputSecond}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '2'? "checked" : false}>두번째 명제</label>
                <div className="checkbox mb-3"></div>
                <input type="radio" name="gener"  id="2" onClick={onClickRadio}/>
            </div>
            <div className="form-floating">
                <input  className="form-control" id="floatingInput" placeholder='성함' value={inputThird} onChange={handleInputThird}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '3'? "checked" : false}>세번째 명제</label>
                <div className="checkbox mb-3"></div>
            <input type="radio" name="gener"  id="3" onClick={onClickRadio} />
            </div>
            <button type="submit" onClick={confirmSubmit} className="w-100 btn btn-lg btn-primary">{buttonTitle}</button>
        </form>
    </div>
    )
}
export default Submit;