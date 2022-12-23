import React from 'react';
import {useNavigate} from 'react-router-dom';
import {Games} from './Games'
import Admin from "../Admin/Admin";
import Monitor from "../Main/Monitor/Monitor";

import Buttons from '../Part/Buttons'
import axios from 'axios';
function Game3() {
  const special = sessionStorage.getItem('special')

  const navigate = useNavigate();
  
  const game = 3

  axios.post ('/stage/gameCheck')
            .then ((res) => {
                console.log ("Admin GAME ",res.data.CURRENT_GAME)
                console.log ("Admin STAGE",res.data.CURRENT_STAGE)
                
                
                 if (game !== res.data.CURRENT_GAME){
                    navigate ('/')
                  }
            });
  return (
    <div className='App'>
      <div >
        game3<Games number="3" />
    </div>
    {special==='2' && <Admin/>}
    <Buttons/>
    </div>
    
  );
}



export default Game3;
