import React, {useEffect, useState} from "react";
import {GameProps, GameStageProps} from "../../GameProps/GameProps";
import {ResultAPI} from "./ResultAPI";
import {ShowAPI} from "../show/ShowAPI";

export default function  Result(props : GameStageProps) {
    const [correctName, setCorrectName] = useState<string[]>();

    const getNameList = ((nameList : string[])=>{
        console.log("NAMELIST : "+nameList);
        setCorrectName(nameList);
    })

    useEffect(()=>{
        ResultAPI(getNameList);
    },[])

    return (
        <div className="Game0" >

            <p>맞추신 분 :</p>
            {correctName?.map ((name, index)=>{
                return (
                    <div key={index}>
                        <p>{name}</p>
                        <ul className={"p-4"}></ul>
                    </div>
                )
            })}
        </div>
    );
}