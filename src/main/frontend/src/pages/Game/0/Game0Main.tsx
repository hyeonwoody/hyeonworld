import React from "react";
import {GameProps} from "../GameProps/GameProps";

export default function Game0(props : GameProps) {


    return (
        <div className="Game0">

            <p>진실 혹은 거짓</p>
            <p>{props.stage}</p>

        </div>
    );
}