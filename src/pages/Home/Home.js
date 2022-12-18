import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';

import './Home.css';

import axios from "axios";

import Admin from "../Admin/Admin"

import {useSelector} from 'react-redux'

import Buttons from '../Part/Buttons'

function Home (pros){
    const [stage,changeStage] = useState(-1)
    const [game, changeGame] = useState(-1)
    

    console.log ("game : ",game)
    console.log ("stage : ",stage)
    const [isLogin, setIsLogin] = useState (pros.isLogin)
    const navigate = useNavigate();
    const special = sessionStorage.getItem('special')
    console.log("스페셜"+special);
    //const state = useSelector(state => state.currentGameReducer)

    const db = require ('../../db/games.json')

    function monitorGame(id){

    }

    function openGame (id)  {
        console.log("오픈게임"+id)
        navigate ('/game'+String(id), {replace:true})
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
            monitorGame(id)
        }
        else if (special === '2'){ //admin
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
                return <li className={"card"+index%7} key={index} id={index} onClick={clickA}>
                <h3 className="card-title" id={index} onClick={clickA}>{game.name}</h3>
                <div className="checkbox mb-3"></div>
                {game.description}</li>
            })}
        </ul>
        {/* {specialMenu} */}
        </div>
        <div>
            {special === '2' && <Admin/>} 
            {special === '3' && (document.location.href = '/monitor')}
            {special === 2 && <div>bbbddb</div>}
            {special === 3 && <div>cccc</div>}
            </div>
        <Buttons/>
        {/* <button type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button> */}
        </main>
    )
}

export default Home;