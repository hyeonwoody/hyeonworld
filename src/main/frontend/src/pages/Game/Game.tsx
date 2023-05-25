import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";

import {GameProps} from "./GameProps/GameProps";

import Game0 from './0/Game0Main';
import Game1 from "./1/Game1Main";
import Game2 from "./2/Game2Main";
import Game3 from "./3/Game3Main";
import Game4 from "./4/Game4Main";
import Game5 from "./5/Game5Main";
import {GameAPI, WaitingAPI} from "./GameAPI";

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
    const [game, setGame] = useState<number>(props.gameId);
    const [stage, setStage] = useState<number> (props.stage);
    const [waitingList, setList] = useState <string[]> ();



    console.log("Game : "+props.gameId);



    useEffect(()=>{
        setGame (props.gameId);

        function getStage (currentStage : number){
            console.log (currentStage);
            if (currentStage != 1)
                waitingApi.closeConnection();
            setStage(currentStage);
        }

        function getWaitingList (list : string[]){
            setList(list);
        }

        console.log ("aa");
            const stageApi = GameAPI (getStage);
            const waitingApi = WaitingAPI (getWaitingList, props.memberId);
        return () => {
            stageApi.closeConnection();
            waitingApi.closeConnection();
        }
    },[])

    return (
        <div className="Game">
            <ul className="p-2 space-y-1"/>
            <p>{}</p>
            <div className="flex mx-2 items-center justify-center rounded-xl group sm:flex space-x-2 space-y-0.1 bg-white bg-opacity-20 shadow-xl hover:rounded-2xl">

                {Object.entries(Games).map(([gameName, gameComponent], index) =>{
                if (game == index){
                    const Component = gameComponent;
                    return <Component memberId={props.memberId} gameId={props.gameId} stage={stage} key={index}/>;
                }
                return null;
            })}
            </div>

        </div>
    )
}



export default Game;