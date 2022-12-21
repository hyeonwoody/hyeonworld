import {React,useEffect,useState} from 'react';
import {useNavigate} from 'react-router-dom';
import '../Games.css';
import {Games} from '../Games'
import Admin from "../../Admin/Admin";
import Monitor from '../../Main/Monitor/Monitor'
import Buttons from '../../Part/Buttons'
import axios from 'axios';

function Game0() {
  const special = sessionStorage.getItem('special')

  const navigate = useNavigate();
  
  const game = 0
  axios.post ('/stage/gameCheck')
            .then ((res) => {
                console.log ("Admin GAME ",res.data.CURRENT_GAME)
                console.log ("Admin STAGE",res.data.CURRENT_STAGE)
                
                
                 if (game !== res.data.CURRENT_GAME){
                    navigate ('/')
                  }
            });
  useEffect(() =>{
    
  },[]);
  return (
    <div className='App'>
      <div>
        game0<Games number="0" />
    </div>
    
    {special==='2' && <Admin game={0}/>}
    {special==='3' && <Monitor/>}
    <Buttons/>
    </div>
    
  );
}



export default Game0;