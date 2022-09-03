import React, {useEffect} from "react";
import { Routes, Route, useNavigate} from 'react-router-dom';

import './Main.css';

import {PersistGate} from 'redux-persist/integration/react'

import Admin from "./Admin/Admin";
import Monitor from "./Monitor/Monitor";

import {store} from '../../store/mainStore';

import { Provider } from 'react-redux';
//import { store, persistor } from './redux/store';



function Main (pros){
    const isLogin = pros.isLogin
    const navigate = useNavigate();
    let special = sessionStorage.getItem('special')

    
    console.log("before",store.getState());
    const db = require ('../../config/db.json')



    const clickA = (e)=>{
        var id = e.target.getAttribute("id");
        var state = store.getState();
        if (special === null){
            console.log("no special",store.getState());
            if (state.CURRENT_GAME === String(id)){
                navigate('/game'+String(id), {replace:true})            
            }
        }
        else if (special === '2'){
            console.log("special",store.getState());
        }
            
        // store.dispatch(changeCurrentGame(0))
        // console.log("yo",store.getState());
        // console.log(e.target.getAttribute("id"))
    }
    
    useEffect(() => {
       
    },
    [])
    return (
        <Provider store={store}>
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
            {special==='2' && <Admin/>}
            {special==='3' && <Monitor/>}
        </main>
        </Provider>
    )
}

export default Main;