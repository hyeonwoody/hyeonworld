import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
function Game0() {
  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <div onClick={aa}>
        game1
    </div>
  );
}



export default Game0;
