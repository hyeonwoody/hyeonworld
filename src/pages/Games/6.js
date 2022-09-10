import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';

import Admin from "../Main/Admin/Admin";
import Monitor from "../Main/Monitor/Monitor";
function Game6() {
  const navigate = useNavigate();
  const special = sessionStorage.getItem('special')
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <main className='App'>
      <div onClick={aa}>
        game6
    </div>
    {special==='2' && <Admin/>}
    {special==='3' && <Monitor/>}
    </main>
  );
}



export default Game6;
