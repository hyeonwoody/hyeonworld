import React, {useState} from "react";
import { Routes, Route, useNavigate} from 'react-router-dom';

import store from '../../../store/mainStore';


function Admin() {

    const [color, setColor] = useState("red");

    const menu = ["Init", "Open", "Tutorial", "Submit", "Check", "Show", "Play", "Result", "Terminate"];
    
    const navigate = useNavigate();
    const changeGameStage = (game_id) => {
        return {
          type: 'STAGE',
          payload: game_id
        }
    }
    window.localStorage.setItem("userName", JSON.stringify("fd"));


    const onClickmenu = (e) => {
        
        e.preventDefault()
        let id = e.target.getAttribute("id");
        console.log(id)
        console.log("Admin,, beforemenu", store.getState())
        store.dispatch(changeGameStage(id))
        console.log("Admin,, aftermenu", store.getState())




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

        </main>
        
    )
}

export default Admin;