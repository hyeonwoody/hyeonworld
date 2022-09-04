import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
function Game3() {
  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <div onClick={aa}>
        game3
    </div>
  );
}



export default Game3;
