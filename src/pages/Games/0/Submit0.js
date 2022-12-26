import React, { useEffect, useState } from 'react';
import axios from 'axios';
import { isDisabled } from '@testing-library/user-event/dist/utils';

let confirm = false


function Submit(){

    const [inputFirst, setFirst] = useState ('');
    const [inputSecond, setSecond] = useState ('');
    const [inputThird, setThird] = useState ('');
    const [inputFalse, setFalse] = useState();
    const [buttonTitle, setbutton] = useState('제출');
    const [text, setText] = useState("")
    const name = sessionStorage.getItem('memberName')
    
    const handleInput = (e) =>{
        switch (e.target.id){
            case '1':
                console.log("첫")
                setFirst(e.target.value);
                break;
            case '2':
                setSecond(e.target.value);
                break;
            case '3':
                setThird (e.target.value);
                break;
            default:
                break
        }
        
    }

    const onClickRadio = ((e) =>{
        setText(" ")
        setFalse(e.target.getAttribute("id"))
    })
    // 

    console.log("거짓은 ? : ",inputFalse)

    function confirmSubmit()  {
        console.log("fdfd")
        let confirm = false
        //var newCount = count.toString()
        // let data ={
        //     NAME: name,
        //     FIRST: inputFirst,
        //     SECOND: inputSecond,
        //     THIRD: inputThird,
        //     AFLASE: inputFalse
        // }
        
        if (inputFalse === undefined){
            alert('거짓 명제를 선택해주세요.')
        }
        else if (window.confirm(inputFalse+"번째 명제가 거짓인가요?")) {
            confirm = true
        }
        else {
        }
        axios.post ('/tmp/submit/0/set', null, {
            params: {
                NAME: name,
                FIRST: inputFirst,
                SECOND: inputSecond,
                THIRD: inputThird,
                AFALSE: inputFalse,
                CONFIRM: confirm
            }
        })
    };
    useEffect(() => {
        
        axios.post ('/tmp/submit/0/get', null, {
                params: {
                    NAME: name
                }
            })
                .then ( (res)=>{

                    //count = res.data.CNT
                    console.log("리절트 ", res.data)
                    confirm = (res.data.CONFIRM === 'true')
                    console.log("컨펌펌", confirm)
                    if (res.data.RESULT_CODE === 0 && confirm){
                        console.log("여길 ", buttonTitle.length)
                        setFirst(res.data.FIRST);
                        setSecond(res.data.SECOND);
                        setThird(res.data.THIRD);
                        setbutton("제출 완료")
                    }
                    else if (res.data.RESULT_CODE === 2){
                        setFirst(res.data.FIRST);
                        setSecond(res.data.SECOND);
                        setThird(res.data.THIRD);
                        setbutton("거짓 명제 선택 후 제출")
                    }
                    else if (!confirm){
                        setFirst(res.data.FIRST);
                        setSecond(res.data.SECOND);
                        setThird(res.data.THIRD);
                        setbutton("제출해주세요")
                    }
                    
                })
    },[])
    function confirmCheck(){
        return confirm? <p>제출완료</p>:<p>제출해주세요.</p>
    }
    return (
    <div>

        <form>
            <div className="form-floating">
                <input  className="form-control" id="1"  value={inputFirst} onChange={handleInput}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '1'? "checked" : false}>첫번째 명제</label>
                <div className="checkbox mb-3"></div>
                <input type="radio" name="gener" id="1" onClick={onClickRadio}/>
            </div>
            <div className="form-floating">
                <input  className="form-control" id="2"  value={inputSecond} onChange={handleInput}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '2'? "checked" : false}>두번째 명제</label>
                <div className="checkbox mb-3"></div>
                <input type="radio" name="gener"  id="2" onClick={onClickRadio}/>
            </div>
            <div className="form-floating">
                <input  className="form-control" id="3"  value={inputThird} onChange={handleInput}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}} checked={inputFalse === '3'? "checked" : false}>세번째 명제</label>
                <div className="checkbox mb-3"></div>
            <input type="radio" name="gener"  id="3" onClick={onClickRadio} />
            </div>
            <button id="submitButton" type="submit" onClick={confirmSubmit} className="w-100 btn btn-lg btn-primary" disabled={!text}>{buttonTitle}</button>
        </form>
    </div>
    )
}
export default Submit;