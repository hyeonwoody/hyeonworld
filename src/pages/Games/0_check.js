import axios from 'axios';
import React, { useEffect, useState } from 'react';


function Check() {
  const [DB, setDB] = useState([])  


  function refresh(){
    axios.post('/member/truth', null, {
    })
        .then (res => {
          console.log ('LEts get ', res.data)
          setDB(res.data)
          console.log("after : ", DB)
  })
        .catch(err => console.log("fcc",err))
  }

  const clickB = (e) => {
    var id = e.target.getAttribute("id")
    console.log("You clcikaa", id)
    axios.post('/member/truthShow', null, {
      params:{
        ID: id
      }
    })
  }

useEffect(() => {
  
},
[DB])
  return (
 <main >
    <ul className="cards">
      {DB.map((person, index) =>{
          console.log("time : ", person)
        return <li className={"card"+index%7} key={index} id={index} onClick={clickB}>
          <h3 className="card-title" id={index} onClick={clickB}>{person.name}</h3>
        <div id={index}>{person.Qfirst}</div>
        <div id={index}>{person.Qsecond}</div>
        <div id={index}>{person.Qthird}</div>
        <div id={index}>{person.iFalse}</div></li>
      
      })}

    </ul>
    
    <button onClick={refresh}>새로고침</button>
    </main>
  );
}

export default Check;