import axios from 'axios';
import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';

import Check from "./0_check";
import Submit from "./0_submit";
import Play from "./0_play";

import Admin from "../Main/Admin/Admin";

import Show from "./0_foo"

function Game0() {
  const navigate = useNavigate();
  const special = sessionStorage.getItem('special')
  const [stage, setGame] = useState('-1')

  const aa = (e) => {
    navigate('/')
  }
  if (special !==2){
  setInterval(doit, 5000)
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
        
    {(stage===2 || stage===3) && <Submit/>}
    {(stage===4 && special === '2') && <Check/>}
    {(stage===4 && special === null) && <Submit/>}
    {stage===5&& <Show/>}
    {stage===6&& <Play/>}
    {stage===7&& <div>stage 7</div>}
    {stage===8&& navigate('/')}
    {special==='2' && <Admin/>}
    </main>
  );
}



export default Game0;
