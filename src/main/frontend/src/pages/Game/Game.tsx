import React, {useEffect, useState} from 'react';
import {useLocation} from "react-router-dom";

import {GameProps} from "./GameProps/GameProps";

import Game0 from './0/Game0Main';
import Game1 from "./1/Game1Main";
import Game2 from "./2/Game2Main";
import Game3 from "./3/Game3Main";
import Game4 from "./4/Game4Main";
import Game5 from "./5/Game5Main";
import {StageAPI, WaitingAPI} from "./GameAPI";
import {wait} from "@testing-library/user-event/dist/utils";

export const Games = {
    "진실 혹은 거짓": Game0,
    "무작위 세 단어": Game1,
    "소수결 게임": Game2,
    "퀴즈퀴즈": Game3,
    "떡 먹은 현우 찾기": Game4,
    "선택지 게임": Game5,
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
    const [waitingList, setList] = useState <string[]> ([]);

    useEffect(()=>{
        setGame (props.gameId);

        function getStage (currentStage : number){
            console.log ("current stage : "+currentStage);

            setStage(currentStage);
        }

        function getWaitingList (list : any){
            console.log("Game : "+list);
            if (typeof list === "string"){
                const removedList: string[] = waitingList.filter((item) => item !== list);
                setList(removedList);
            }

            else {
                setList(list);
            }
            if (stage != 1)
                stageApi.closeConnection();
        }

        console.log ("aa");
        const waitingApi = WaitingAPI (getWaitingList, props.memberId);
        const stageApi = StageAPI (getStage, props.memberId);

        return () => {
            stageApi.closeConnection();
            waitingApi.closeConnection();
        }
    },[waitingList])

    return (
        <div className="Game">
            <ul className="p-2 space-y-1"/>
            <div className="flex mx-2 items-center justify-center rounded-xl group sm:flex space-x-2">

                {Object.entries(Games).map(([gameName, gameComponent], index) =>{
                if (game == index){
                    const Component = gameComponent;
                    return (
                        <div>
                            <p>{gameName}</p>
                            <Component memberId={props.memberId} gameId={props.gameId} stage={stage} key={index}/>
                        </div>
                    );
                }
            })}

            </div>
            {stage == 1 && <div className={"waitngList"}>
                {
                    waitingList.map((memberName :string)=>{
                        return (
                            <p>{memberName} 님</p>
                        );
                    })  }
                <p>기다리고 있습니다.</p>
            </div>}

        </div>
    )
}



export default Game;