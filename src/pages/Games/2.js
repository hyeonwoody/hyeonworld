import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
import {store} from '../../store/mainStore';

import Admin from "../Main/Admin/Admin";
import Monitor from "../Main/Monitor/Monitor";

function Game2() {
  const special = sessionStorage.getItem('special')

  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <main>
      <div onClick={aa}>
        game2
    </div>
    {special==='2' && <Admin/>}
    {special==='3' && <Monitor/>}
    </main>
    
  );
}



export default Game2;
