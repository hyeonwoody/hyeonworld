import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
function Game6() {
  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <div onClick={aa}>
        game6
    </div>
  );
}



export default Game6;
