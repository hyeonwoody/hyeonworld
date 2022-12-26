import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';

import './Home.css';

import axios from "axios";

import Admin from "../Admin/Admin"

import {useSelector} from 'react-redux'
import  SCORES from '../../config/scores'
import Buttons from '../Part/Buttons'

function Home (pros){
    const [stage,changeStage] = useState(-1)
    const [game, changeGame] = useState(-1)
    
    var gameIndex = -1

    console.log ("game : ",game)
    console.log ("stage : ",stage)
    const [isLogin, setIsLogin] = useState (pros.isLogin)
    const navigate = useNavigate();
    const special = sessionStorage.getItem('special')
    //const state = useSelector(state => state.currentGameReducer)

    const db = require ('../../db/games.json')

    function monitorGame(id){

    }

    function openGame (id)  {
        console.log("오픈게임"+id)
        navigate ('/game'+String(id))
        // axios.post('/game/onGame', null, {
        //     params: {
        //         GAME: id,
        //         SPECIAL: special
        //     }
        // })
        //             .then (res => {
        //                 console.log("what is this ",res.data.RESULT_CODE)
        //                 console.log(typeof(id))
        //                 console.log(typeof(res.data.RESULT_CODE))
        //                 if (res.data.RESULT_CODE === id){
        //                     navigate('/game'+String(id), {replace:true})
        //                 }
        //             })
        //             .catch(err => console.log(err))
    };

    

    const clickA = (e)=>{
        let id = parseInt(e.target.getAttribute("id"));
        
        axios.post ('/stage/gameCheck')
            .then ((res) => {
                console.log ("Admin GAME ",res.data.CURRENT_GAME)
                console.log ("Admin STAGE",res.data.CURRENT_STAGE)
                console.log ("Admin id",id)
                 changeGame(res.data.CURRENT_GAME)
                 changeStage(res.data.CURRENT_STAGE)
            });

        if (special === '3'){ //monitor
            if (game === id){
                openGame(id);
            }
        }
        else if (special === '45678'){ //admin
            if (game === id){
                openGame(id);
            }
            
        }
        else if (special === '1'){ //players
            if ( game === id)
            openGame(id)
        }
        
    }

    return (
        <main>
        <div className="container">
        <h2>게임을 선택해주세요.</h2>
        <ul className="cards">
            {db.games.map((game, index) =>{
                if (game.play){
                    gameIndex++    
                return <li className={"card"+gameIndex%7} key={index} id={index} onClick={clickA}>
                <h3 className="card-title" id={index} onClick={clickA}>{game.name}{special!=='1'?index:""}</h3>
                <div className="checkbox mb-3"></div>
                {game.description}</li>
                }
            })}
        </ul>
        {/* {specialMenu} */}
        </div>
        <div>
            {special === '45678' && <Admin game={-1}/>} 
            
            </div>
        <Buttons/>
        {/* <button type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button> */}
        </main>
    )
}

export default Home;