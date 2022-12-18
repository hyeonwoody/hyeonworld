import React from 'react';
import {useNavigate} from 'react-router-dom';
import Games from './Games'
import Admin from "../Admin/Admin";
import Monitor from "../Main/Monitor/Monitor";

import Buttons from '../Part/Buttons'
import axios from 'axios';
function Game5() {
  const special = sessionStorage.getItem('special')

  const navigate = useNavigate();
  
  const game = 5

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
        game5<Games />
    </div>
    {special==='2' && <Admin/>}
    {special==='3' && <Monitor/>}
    <Buttons/>
    </main>
    
  );
}



export default Game5;
