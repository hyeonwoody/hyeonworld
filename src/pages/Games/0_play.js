import axios from 'axios';
import React, { useEffect, useState } from 'react';


function Play() {
  const [show, setShow] = useState([])  
  
  const name = sessionStorage.getItem('memberName')
  axios.post('/member/truthShow', null, {

  })
            .then (res => {
              setShow(res.data)
              })  

const onClickAnswer = (e) =>{
  var id = e.target.getAttribute("id")
  console.log("Play Answer", id)
  var resultcode = 0
  axios.post('/member/truthAnswer', null, {
    params : {
      ID : id
    }
  })
              .then (res => {
                console.log("Answer 결과 " , res.data.resultcode);
                resultcode = res.data.resultcode
                })
  axios.post('/member/truthCorrect', null, {
    params : {
      NAME : name,
      Correct : resultcode
    }
  })

}

useEffect(() => {
  
},
[])
  return (
 <main >
      <div>거짓을 골라주세요</div>
      <button className="w-100 btn btn-lg btn-primary" id ="1" onClick={onClickAnswer}>{show.Qfirst}</button>
      <div className="checkbox mb-3"></div>
      <button className="w-100 btn btn-lg btn-primary" id ="2" onClick={onClickAnswer}>{show.Qsecond}</button>
      <div className="checkbox mb-3"></div>
      <button className="w-100 btn btn-lg btn-primary" id ="3" onClick={onClickAnswer}>{show.Qthird}</button>
    </main>
  );
}



export default Play;
