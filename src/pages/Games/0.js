import axios from 'axios';
import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';

import Admin from "../Main/Admin/Admin";




function Game0() {
  const navigate = useNavigate();
  const special = sessionStorage.getItem('special')

  const [stage, setGame] = useState('-1')
  const [inputFirst, setFirst] = useState ('')
  const [inputSecond, setSecond] = useState ('')
  const [inputThird, setThird] = useState ('')
  var inputFalse 

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
  inputFalse = parseInt ( e.target.getAttribute("id"))
  console.log (inputFalse )
})

  const aa = (e) => {
    navigate('/')
  }
  

  const onClickSubmit = () => {
    const name = sessionStorage.getItem('memberName')
    axios.post('/member/onLogout', null, {
        params: {
            name: name,
            first: inputFirst,
            second: inputSecond,
            third: inputThird,
            iFalse: inputFalse
        }
    })
                .then (res => {
                    console.log("결과 " , res.data.resultcode);
                    if (res.data.resultcode){
                        sessionStorage.removeItem ("memberName")
                        document.location.href = '/'
                    }
                    
                })
                .catch (err => console.log (err))
    
    
}

  


  if (special !==2){
  setInterval(doit, 10000)
  }
  function doit(){
    axios.post('/game/getStage', null, {
    })
        .then (res => {
          console.log ('yes ', res.data)
          setGame(res.data)
  })
  .catch(err => console.log("fcc",err))
  }


useEffect(() => {
  
},
[stage])
  return (
 <main className='App'>
      <div  onClick={aa}/>
        game0
    
    {stage===1 && <div>stage 1</div>}
    {stage===2 && <div>stage 2</div>}
    {stage===3 && 
      <form><div className="form-floating">
      <input  className="form-control" id="floatingInput" placeholder='성함' value={inputFirst} onChange={handleInputFirst}></input>
      <label htmlFor="floatingInput" style={{'color' : "#181717"}}>첫번째 명제</label>
      <div className="checkbox mb-3"></div>
        <input type="radio" name="gener" id="1" onClick={onClickRadio}/>
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
  <button type="submit" onClick={onClickSubmit} className="w-100 btn btn-lg btn-primary"  >로그인</button></form>
      
            
            }
    {stage===4 && <div>stage 4</div>}
    {stage===5&& <div>stage 5</div>}
    {stage===6&& <div>stage 6</div>}
    {stage===7&& <div>stage 7</div>}
    {stage===8&& <div>stage 8</div>}
    {special==='2' && <Admin/>}
    </main>
  );
}



export default Game0;
