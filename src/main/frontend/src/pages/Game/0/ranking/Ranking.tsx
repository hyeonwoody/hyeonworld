import React, {useEffect} from "react";
import {GameStageProps} from "../../GameProps/GameProps";
import {RankingAPI} from "./RankingAPI";

export default function Ranking (props : GameStageProps){

    useEffect(()=>{
        RankingAPI(props.memberId);
    },[])

    return (
        <div className="Game0">

            <p>랭킹</p>

        </div>
    );
}