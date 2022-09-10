import React, {useEffect} from "react";
import {useNavigate} from 'react-router-dom';

import './Main.css';


import Admin from "./Admin/Admin";
import Monitor from "./Monitor/Monitor";

import {store} from '../../store/mainStore';


import axios from "axios";

//import { store, persistor } from './redux/store';



function Main (pros){
    const isLogin = pros.isLogin
    const navigate = useNavigate();
    const special = sessionStorage.getItem('special')

    let check = 1

    console.log("Main,, before",store.getState());
    const db = require ('../../config/db.json')

    function openGame (id)  {
        axios.post('/game/onGame', null, {
            params: {
                GAME: id,
                SPECIAL: special
            }
        })
                    .then (res => {
                        if (String(res.data.resultcode) === id){
                            navigate('/game'+String(id), {replace:true})
                        }
                    })
                    .catch(err => console.log(err))
    };

    

    const onLogout = () => {
        const name = sessionStorage.getItem('memberName')
        axios.post('/member/onLogout', null, {
            params: {
                NAME: name,
            }
        })
                    .then (res => {
                        console.log("결과 " , res.data.resultcode);
                        if (res.data.resultcode){
                            sessionStorage.removeItem ("memberName")
                            document.location.href = '/'
                        }
                        
                    })
                    .catch (err => console.log (err))
        
        
    }

    const clickA = (e)=>{
        var id = e.target.getAttribute("id");
        var stage= store.getState().STAGE
        if (special === null){
            openGame(id)
        }
        else if (special === '2'){    
            if (stage === '1'){
                openGame(id);
            }
        }
        else if (special === '1'){
            
        }
        

        
        // store.dispatch(changeCurrentGame(0))
        // console.log("yo",store.getState());
        // console.log(e.target.getAttribute("id"))
    }

    useEffect(() => {
        
    },
    [])
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
            {special ==='2' && <Admin/>} 
            {special ==='3' && (document.location.href = '/monitor')}
            
            <button type='button' className="btn btn-danger" onClick={onLogout}>로그아웃</button>
        </main>
    )
}

export default Main;