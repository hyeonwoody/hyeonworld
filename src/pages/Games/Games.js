import React,{useState, useEffect} from "react";
import { useQuery } from "react-query";
import { Navigate } from "react-router-dom";

import Spinner from '../Part/Spinner'
import Tutorial from '../Stages/GenericTutorial'
import Submit from '../Stages/GenericSubmit'
import Check from '../Stages/GenericCheck'
import Show from '../Stages/GenericShow.js'
import Play from '../Stages/GenericPlay'
import Result from '../Stages/GenericResult'
import Ranking from '../Stages/GenericRanking'
import Terminate from '../Stages/GenericTerminate'
import Config from '../../config/config'
var onPlaying = true
function Games(props){

    const config = new Config()
    
    
    const [currentGame, changeGame] = useState(parseInt(props.number));
    const [currentStage, changeStage] = useState(1);


    //const stage = ["Init", "Open", Tutorial, Submit, Check, Show, Play, Result, Terminate];
    function pressBack(){
        const pressBack = document.getElementById('pressBack')
        if (pressBack.style.display === 'none'){
            pressBack.style.display = 'block'
        }
            
        
    }


    

    function switchView(stage){
        const show = document.getElementById (stage)
        console.log("스위칭")
        if (show.style.display === 'none'){
            show.style.display = 'block'
        }
        const hide = document.getElementById (currentStage)
        hide.style.display = 'none'
    }
    //switchView(currentStage)

    const {data, status} = useQuery('getStage',  ()=>{
        fetch('/stage/get',{
            method: "POST", //GET , POST, PUT, DELETE
        })
            .then ((res)=> res.json())
            .then ((data)=> {
                if (data.CURRENT_GAME === undefined|| data.CURRENT_STAGE === undefined){

                }
                else if (data.CURRENT_STAGE !== currentStage){
                    console.log("aaa",data.CURRENT_STAGE)
                    switchView(data.CURRENT_STAGE)
                    changeStage(data.CURRENT_STAGE)
                    //currentStage = obj.currentStage
                } 
                        
                else{
                }
                if (data.CURRENT_GAME !== currentGame){
                    pressBack()
                }
                else{
                }
            })
    },{
        enabled: true,
        refetchInterval: 6000,
        cacheTime: Infinity,
    });
    console.log("#Games/getStage 상태", status)
    if (status === 'loading'){
        console.log("로딩중")
    }
    if (status === 'error'){
        console.log ('에러 발생')
    }
    if (status === 'success'){
        console.log("성공")

    }
   
    	
    useEffect(() => {
   
  },[data]);
   
    function renderStage(){
        var special = parseInt(sessionStorage.getItem('special'))

        switch (currentStage){
            case 2: 
                return <Tutorial game={currentGame}/>
            case 3:
                return <Submit game={currentGame}/>
            case 4: //check
                
                if (special === 2){
                    return <Check game={currentGame}/>
                }
                else  {
                    return <Show game={currentGame}/>
                }
            case 5: //show
                if (special === 2){
                    return <Check game={currentGame}/>
                }
                else if (special === 3){
                    return <Show game={currentGame}/>
                }
                else {
                    return <Play game={currentGame}/>
                }
            case 6: //play
                
                if (special === 1){
                    return <Play game={currentGame}/>
                }
                else if (special === 2){
                    return <Check game={currentGame}/>
                }
                else {
                    return <Show game={currentGame}/>
                }
            case 7: //result
                if (special !== 1){
                    return <Result game={currentGame}/>
                }
                else {
                    return <Play game={currentGame}/>
                }
            case 8: //ranking
                if (special !== 1){
                    return <Ranking game={currentGame}/> 
                }
                else {
                    return <Show game={currentGame}/>
                }
            case 9:
                return <Terminate /> 
            default:
                return <div/>
        }
    }
    return (
        
        <div>
            
            {config.stages.map ((stage, index) => {
            return <div id={index} style={{"display": "none"}} key={stage}></div>
            })}

            {currentStage === 1? <Spinner/>:renderStage()}

        
            <p id="pressBack" style={{"display": "none"}}>뒤로가기를 눌러주세요 </p>
        </div>
    )
} 




export {Games};

