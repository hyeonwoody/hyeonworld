import React, {Component, useEffect, useState} from "react";
import {GameProps} from "../GameProps/GameProps";

import Tutorial from "./Tutorial";
import Submit from "./submit/Submit";
import Check from "./Check";
import Show from "./Show";
import Play from "./Play";
import Result from "./Result";
import Ranking from "./Ranking";
import Done from "./Done";

export const Stages = {
    0: null,
    1: null,
    2: Tutorial,
    3: Submit,
    4: Check,
    5: Show,
    6: Play,
    7: Result,
    8: Ranking,
    9: Done,
}

export default function Game0(props : GameProps) {


    console.log("GAME 0 Stage : "+props.stage);
    useEffect( ()=>{

    },[])
    return (
        <div className="Game0">
            {Object.entries(Stages).map(([index, stageComponent]) =>{
                if (props.stage == Number(index) && stageComponent != null){
                    const Component = stageComponent;
                    return (
                        <div>
                            <Component/>
                        </div>
                    );
                }
            })}

        </div>
    );
}