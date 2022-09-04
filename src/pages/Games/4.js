import React, { useEffect, useState } from 'react';
import {useNavigate} from 'react-router-dom';
function Game4() {
  const navigate = useNavigate();
  
  const aa = (e) => {
    navigate('/')
  }
  
  return (
    <div onClick={aa}>
        game4
    </div>
  );
}



export default Game4;
