import React, {useEffect, useState} from "react";

import { Routes, Route, useNavigate} from 'react-router-dom';

import axios from "axios";
import Config from '../../config/config'
//import { addToCart } from '../../actions/index'; // 액션 가져오기
//import { useSelector, useDispatch } from 'react-redux'; // 리덕스 후크 가져오기


function Admin(props) {
    const config = new Config()
    const [aa, setAA] = useState ('');
    const navigate = useNavigate();
    const changeGameStage = (stage) => {
        return {
          type: 'STAGE',
          payload: stage
        }
    }

    const changeCurrentGame = (game) => {
        return {
            type: 'CURRENT_GAME',
            payload: game
        }
    }
function Open(){
    const open = document.getElementById('Open')
        if (open.style.display !== 'none'){
            open.style.display = 'none'
        }
            
        else {
            open.style.display = 'block'
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
const onClickOpen = () => {
    //const game = parseInt(document.getElementById("game").value)
    console.log("다음은요",aa)
    axios.post ('/stage/game', null,{
        params:{
            CURRENT_GAME: aa
        }
    })
        // .then ((res) => {
        //     console.log ("Admin result ",res.data)
        // });
}

function onClickInit ()  {
    const familySide = parseInt(document.getElementById("familySide").value)
    const players = parseInt(document.getElementById("persons").value)

    axios.post ('/onInit/admin', null,{
        params:{
            FAMILYSIDE: familySide,
            PLAYERS: players
        }
    })
    axios.post ('/onLog/admin', null,{
        
    })
        .then ((res)=>{
            console.log("aaf",res.data.RESULT_CODE)
        })
}

    const onClickmenu = (e) => {
        
        //e.preventDefault()
        const menu = parseInt(e.target.getAttribute("id"))
        axios.post ('/stage/current', null,{
            params:{
                CURRENT_STAGE: menu
            }
        })
            .then ((res) => {
                console.log ("Admin result ",res.data.RESULT_CODE)
                
            });       
        switch (menu){
            case 0:
                axios.post ('/onLog/admin', null,{
        
                })
                    .then ((res)=>{
                        console.log("aaf",res.data.RESULT_CODE)
                    })
                Init();
                break;
            case 1:
                Open();
                break;
            case 2:
                console.log("it is 2")
                break;

            case 3:
                console.log("it is 3")
                break;
            case 4:
                console.log("it is 4")
                break;
            case 5:
                console.log("it is 5")
                break;
            case 6:
                console.log("it is 6")
                break;
            case 9:
                console.log("간다")
                axios.post ('/terminate/'+props.game+'/set', null, {
            
                })
                .then (()=>{
                    console.log("보냈다고")
                })
                break;
        }
        

        

    }
    
    useEffect(() => {
        
    },)
    function process(){
        switch (props.game){
            case -1:
                return <p>피는 물보다 진하다</p>
            case 0:
                return <p>Tutorial Submit Check Show Play Result (Ranking)</p>
            default:
                return <p>아직몰라</p>;
        }
    };

    const handleInput = (e) =>{
        switch (e.target.id){
            case 'game':
                console.log("첫", aa)
                setAA(e.target.value);
                console.log("둘", aa)
                break;
            default:
                break
        }
        
    }

    return (
        
        <div>
            
            {config.stages.map ((menu, index) => {
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
        {process()}
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
        <form id="Open" style={{"display": "none"}}>
        <div className="form-floating">
                <input  className="form-control" id="game" value={aa|| ''} onChange={handleInput}></input>
                <label htmlFor="floatingInput" style={{'color' : "#181717"}}>게임</label>
            </div>
            <button type="submit" onClick={onClickOpen} className="w-100 btn btn-lg btn-primary"  >제출</button>
        </form>
        </div>
        
    )
}

export default Admin;