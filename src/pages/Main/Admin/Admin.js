import React, {useState} from "react";
import { Routes, Route, useNavigate} from 'react-router-dom';

import store from '../../../store/mainStore';

import axios from "axios";

function Admin() {

    const menu = ["Init", "Open", "Tutorial", "Submit", "Check", "Show", "Play", "Result", "Terminate"];
    
    const navigate = useNavigate();
    const changeGameStage = (game_id) => {
        return {
          type: 'STAGE',
          payload: game_id
        }
    }

function Init () {
  const init = document.getElementById('Init')
        if (init.style.display !== 'none'){
            init.style.display = 'none'
        }
            
        else {
            init.style.display = 'block'
}
}


const onClickInit = () => {
        const familySide = parseInt(document.getElementById("familySide").value)
        const persons = parseInt(document.getElementById("persons").value)
        axios.post('/member/onConfig', null, {
            params: {
                familySide: familySide,
                persons: persons
            }
        })
}

    const onClickmenu = (e) => {
        
        e.preventDefault()
        let id = e.target.getAttribute("id");
        console.log("now : ",id)
        console.log("Admin,, beforemenu", store.getState())
        

        store.dispatch(changeGameStage(id))
        console.log("Admin,, aftermenu", store.getState())

        axios.post('/game/onStage', null, {
            params: {
                stage: id,
                special: 2
            }
        })
                .then (res => {
                    console.log("consquence : ",id)
                })
                .catch(err => console.log("fcc",err))
        switch(id) {
            case '0':
              return Init()
              //return Object.assign({CURRENT_GAME: action.payload });
              break;
            case 'STAGE':
              return
            default:
              return 
          }




        




    }

    return (
        <main>
            
            {menu.map ((menu, index) => {
                return <button id={index}  onClick={onClickmenu} key={index}>{menu}</button>
            })}
        {/* <div>1 Open Tuturial Submit Check Show Submit |Play| Result
        </div>
        <div>2 Open Tuturial |Submit Check Show| xPlay Resultx2 
        </div>
        <div>3 Open Tuturial xShow Submit Check |Play| Resultx2
        </div>
        <div>4 Open Tuturial xShow Submitx2 |Check Play| Result
        </div>

        <div>5 |Open Tuturial Submit Check Show Play Result|
        </div>

        <div>6 Open |Tuturial| Submit |Check| Show Play Result
        </div> */}
        <form id="Init" style={{"display": "none"}}>
            <div className="form-floating">
                <input  className="form-control" id="familySide"></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}}>가족 (0:외가 1:친가)</label>
            </div>
            <div className="form-floating">
                <input  className="form-control" id="persons"></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}}>인원</label>
            </div>
            <button type="submit" onClick={onClickInit} className="w-100 btn btn-lg btn-primary"  >제출</button>
        </form>    

        </main>
        
    )
}

export default Admin;