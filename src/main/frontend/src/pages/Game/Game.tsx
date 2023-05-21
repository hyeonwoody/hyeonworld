import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";

import {GameProps} from "./GameProps/GameProps";

import Game0 from './0/Game0Main';
import Game1 from "./1/Game1Main";
import Game2 from "./2/Game2Main";
import Game3 from "./3/Game3Main";
import Game4 from "./4/Game4Main";
import Game5 from "./5/Game5Main";
import {StageAPI} from "./StageAPI";


export const Games = {
    "진실혹은거짓": Game0,
    "무작위세단어": Game1,
    "소수결게임": Game2,
    "퀴즈퀴즈": Game3,
    "떡먹은현우찾기": Game4,
    "선택지게임": Game5,
}

type stateData = {
    id: number;
    rootCall: (data : boolean, loginName :string)=>void;
    name: string;
}

function Game(props : GameProps) {
    // IP주소 변수 선언
    const location = useLocation();
    const [game, setGame] = useState<number>(props.id);
    const [stage, setStage] = useState<number> (0);

    console.log("Game : "+props.id);

    if (game === props.id)
        console.log("!true");
    else {
        console.log("!false");

    }


    useEffect(()=>{
        setGame (props.id);
        function getStage (currentStage : number){
            setStage(currentStage);
        }
        const stageApi = StageAPI ("/api/game-stage/players", getStage);
        return () => {
            stageApi.closeConnection();
        }
    },[])

    return (
        <div className="Game">
            <ul className="p-2 space-y-1"/>
            <div className="flex mx-2 items-center justify-center rounded-xl group sm:flex space-x-2 space-y-0.1 bg-white bg-opacity-20 shadow-xl hover:rounded-2xl">
            {Object.entries(Games).map(([gameName, gameComponent], index) =>{
                if (game == index){
                    const Component = gameComponent;
                    console.log("다왔");
                    return <Component id={props.id} stage={props.stage} key={index}/>;
                }
                return null;
            })}
            </div>
            <p>{stage}</p>
        </div>
    )
}



export default Game;