import React, {useEffect, useState} from "react";
import {GameStageProps} from "../../GameProps/GameProps";
import {Special} from "../../../../configuration/special/SpecialConfig";
import {CheckAPI} from "./CheckAPI";

export default function Check (props : GameStageProps) {

    const special = new Special();

    const Admin = () =>{
        const [playerList, setPlayer] = useState<Player[]> ([]);
        const [playerNameList, setPlayerName] = useState<String[]> ([]);
        interface Player{
            name: string,
            input: string[],
            inputFalse: number,
        }

        const getPlayer = (name : string, input : string[], inputFalse : number)=>{
            const player : Player = {
                name: name,
                input: input,
                inputFalse: inputFalse
            };

            setPlayerName((prevPlayerNameList) => {
                console.log("QQQ"+player.name);
                if (!prevPlayerNameList.includes(player.name)){
                    setPlayer([...playerList, player]);
                    console.log("vcvvvv"+prevPlayerNameList);
                    console.log("zzz"+player.name);
                    return [...prevPlayerNameList, player.name];
                }
                console.log("VV"+player.name);
                return prevPlayerNameList;
            });

            // setPlayer((prevPlayerList) => {
            //     if (!prevPlayerList.includes(player)){
            //
            //         return [...prevPlayerList, player];
            //     }
            //     return prevPlayerList;
            // });
        }

        CheckAPI(getPlayer, props.memberId);

        return(
            <div>
                <p>aa</p>
                {playerList.map((player: Player, i: number) => {
                    return (
                        <div>
                            <p/>{playerList.length}
                            <p>{player.name}</p>
                            <p>{player.input}</p>
                        </div>

                )
                })}
            </div>
        )

    }

    const Player = () =>{
        return (
            <p>player</p>
        )
    }



    return (
        <div className="Game0">

            {special.adminId === props.memberId ? <Admin/> : <Player/> }
            <p>첵</p>

        </div>
    );
}