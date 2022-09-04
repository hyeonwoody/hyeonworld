import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
function Game5() {
  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <div onClick={aa}>
        game5
    </div>
  );
}



export default Game5;
