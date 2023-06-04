import React, {useEffect, useState} from "react";
import {GameStageProps} from "../../GameProps/GameProps";
import {RankingAPI} from "./RankingAPI";

export interface MemberScore{
    memberName :string,
    totalScore : number
}

export default function Ranking (props : GameStageProps){
    const [list, setList] = useState<MemberScore[]>();
    const getList = ((memberList : MemberScore[])=>{
        setList(memberList);
    })

    useEffect(()=>{
        RankingAPI(getList);
    },[])

    return (
        <div className="Game0">

            <p>랭킹</p>
            {list?.map ((memberScore : MemberScore)=>{
                return (
                    <div key={memberScore.memberName}>
                        <p>{memberScore.memberName}</p>
                        <p>{memberScore.totalScore}</p>
                        <ul className={"p-4"}></ul>
                    </div>
                )
            })}
        </div>
    );
}