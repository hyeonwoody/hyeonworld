import React from 'react';
import {useNavigate} from 'react-router-dom';
import {Games} from './Games'
import Admin from "../Admin/Admin";
import Monitor from "../Main/Monitor/Monitor";

import Buttons from '../Part/Buttons'
import axios from 'axios';
function Game2() {
  const special = sessionStorage.getItem('special')

  const navigate = useNavigate();
  
  const game = 2

  axios.post ('/stage/gameCheck')
            .then ((res) => {
                console.log ("Admin GAME ",res.data.CURRENT_GAME)
                console.log ("Admin STAGE",res.data.CURRENT_STAGE)
                
                
                 if (game !== res.data.CURRENT_GAME){
                    navigate ('/')
                  }
            });
  
  return (
    <main className='App'>
      <div>
        game2<Games number="2" />
    </div>
    {special==='2' && <Admin/>}
    <Buttons/>
    </main>
    
  );
}



export default Game2;
