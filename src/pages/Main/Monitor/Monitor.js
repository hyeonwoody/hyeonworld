import React, {useEffect, useState} from 'react';
import axios from "axios";

import Open from "./Monitor_Open";
import Tutorial from "./Monitor_Tutorial";
import Submit from "./Monitor_Submit";
import Check from "./Monitor_Check";
import Show from "./Monitor_Show";
import Play from "./Monitor_Play";
import Result from "./Monitor_Result";

function Monitor() {
    const [stage, setStage] = useState('-1')
    const [game, setGame] = useState('-1')
    useEffect(() => {
        axios.post('/game/onStage', null, {
        })
                .then (res => {
                    setStage(res.data.resultcode)
                    console.log("Monitor stage : ", stage)
                })
                .catch(err => console.log("fcc",err))

    axios.post('/game/onGame', null, {
    })
                .then (res => {
                    setGame(res.data.resultcode)
                    console.log("Monitor  game : ", game)
                })
                .catch(err => console.log("fcc",err))
    },
    [stage, game])
    
    return (
        
        <main className = 'App'>
            {stage==='1' &&<Open whatgame = {game}/> }
           {stage==='2' &&<Tutorial whatgame = {game}/> }
           {stage==='3' &&<Submit whatgame = {game}/> }
           {stage==='4' &&<Check whatgame = {game}/> }
           {stage==='5' &&<Show whatgame = {game}/> }
           {stage==='6' &&<Play whatgame = {game}/> }
           {stage==='7' &&<Result whatgame = {game}/> }
        </main>
        
    )
}

export default Monitor;