import React, {useState} from "react";
import {useNavigate} from 'react-router-dom';

import './Home.css';

import axios from "axios";

import Admin from "../Admin/Admin"
import {store} from '../../store/mainStore'

import {useSelector} from 'react-redux'

function Home (pros){
    let [stage,changeStage] = useState(store.getState().STAGE)
    let [game, changeGame] = useState(store.getState().CURRENT_GAME)
    

    const [isLogin, setIsLogin] = useState (pros.isLogin)
    const navigate = useNavigate();
    const special = sessionStorage.getItem('special')
    console.log("스페셜"+special);
    const state = useSelector(state => state.currentGameReducer)
    
    const db = require ('../../db/games.json')

    function openGame (id)  {
        console.log("오픈게임"+id)
        axios.post('/game/onGame', null, {
            params: {
                GAME: id,
                SPECIAL: special
            }
        })
                    .then (res => {
                        console.log("what is this ",res.data.resultcode)
                        console.log(typeof(id))
                        console.log(typeof(res.data.resultcode))
                        if (res.data.resultcode === id){
                            navigate('/game'+String(id), {replace:true})
                        }
                    })
                    .catch(err => console.log(err))
    };

    const onLogout = () => {
        const name = sessionStorage.getItem('memberName')

        axios.post('/onLog/in', null, {
            params: {
                NAME: name,
            }
        })
                    .then (res => {
                        console.log("결과 " , res.data.resultcode);
                        if (res.data.resultcode){
                            sessionStorage.removeItem ("memberName")
                            sessionStorage.removeItem("special")
                            setIsLogin (false)
                            document.location.href = '/'
                            
                        }
                        
                    })
                    .catch (err => console.log (err))
        
        
    }

    const clickA = (e)=>{
        let id = e.target.getAttribute("id");

        game = store.getState().CURRENT_GAME
        stage = store.getState().STAGE
        console.log("this id"+id)
        
        console.log("this stage : "+stage)

        console.log("this game: "+game)
        if (special === null){
            openGame(id)
        }
        else if (special === '2'){    
            if (stage === '1'){
                console.log("뭐야")
                openGame(id);
            }
            
        }
        else if (special === '1'){
            
        }
        
    }

    return (
        <main>
        <div className="container">
        <h2>게임을 선택해주세요.</h2>
        <ul className="cards">
            {db.games.map((game, index) =>{
                return <li className={"card"+index%7} key={index} id={index} onClick={clickA}>
                <h3 className="card-title" id={index} onClick={ clickA}>{game.name}</h3>
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
        <button type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button>
        </main>
    )
}

export default Home;