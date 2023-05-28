import React, {useEffect, useState} from "react";
import {GameProps} from "../GameProps/GameProps";

export default function Game0(props : GameProps) {


    console.log("GAME 0 Stage : "+props.stage);
    useEffect( ()=>{

    },[])
    return (
        <div className="Game0">

            <p>{props.stage}</p>

        </div>
    );
}