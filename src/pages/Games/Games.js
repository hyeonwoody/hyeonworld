import React,{useState, useEffect} from "react";
import { useQuery } from "react-query";
import { Navigate } from "react-router-dom";
import {io} from 'socket.io-client'


import Tutorial from '../Stages/GenericTutorial'
import Submit from '../Stages/GenericSubmit'
import Check from '../Stages/GenericCheck'
import Show from '../Stages/GenericShow.js'
import Play from '../Stages/GenericPlay'
import Result from '../Stages/GenericResult'
import Terminate from '../Stages/GenericTerminate'

function Games(props){

    
    const socket = io('http://172.30.1.14:3005');

    const [currentGame, changeGame] = useState(parseInt(props.number));
    const [currentStage, changeStage] = useState(1);
    const stage = ["Init", "Open", "Tutorial", "Submit", "Check", "Show", "Play", "Result", "Terminate"];
    
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
    console.log("넘버 값 parse, ", props.number)
    console.log("커렌트", currentGame)

    // /**
    //  * standard
    //  * @returns 
    //  */
    // const usePosts = () => useQuery(['posts', 'list'], fetchPosts)

    // const usePost = (id)=>
    //     useQuery(['posts', 'detail', id], ()=> fetchPost(id))
    // const useReactQuerySubscription = () =>{
    //     React.useEffect(()=>{
    //         const WebSocket = new WebSocket ('wss://echowebsocket.org/')
    //         WebSocket.onopen=()=>{
    //             console.log('connected')
    //         }

    //         WebSocket.onmessage = (event) =>{ //event based subsription
    //             const data = JSON.parse (event.data)
    //             const queryKey = [...data.entity, data.id].filter(Boolean)
    //             queryClient.invalidateQuery(queryKey)
    //         }
            
    //         WebSocket.onmessage = (event) =>{ //partial - update
    //             const data = JSON.parse (event.data)
    //             queryClient.setQueriesData (data.entity, (oldData)=> {
    //                 const update = (entity) =>
    //                     entity.id === data.id ? {...entity, ...data.payload} : entity
    //                 return Array.isArray (oldData) ? oldData.map (update) : update(oldData)
    //             })
    //         }
    //         return ()=>{
    //             WebSocket.close()
    //         }
    //     },[])
    // }
    	
    useEffect(() => {
    console.log("유스 이펟ㄱ트")
    socket.open()
    socket.on('gameStage', async (obj) => {
        console.log(obj)
        if (obj.CURRENT_STAGE !== currentStage){
            switchView(obj.CURRENT_STAGE)
            changeStage(obj.CURRENT_STAGE)
            //currentStage = obj.currentStage
        } 
        
        else{
            console.log("no 스위치")
        }
        if (obj.CURRENT_GAME !== currentGame){
            pressBack()
            console.log("달라요")
            console.log("커렌트", currentGame)
            console.log("옵젝", obj.CURRENT_GAME)
        }
        else{
            console.log("똑같아요")
        }
    });
    return (()=>{
        socket.close()
    });
  }, [socket]);
    // const {isLoading, error,data,isFetching} = useQuery ('repoData', ()=>
    // // axios.get (
    // //     "https://api.github.com/repos/tannerlinsley/react-query"
    // // ).then ((res)=>res.json())
    
    // // axios.get('/stage', null, {
    // //         params: {
    // //             NAME : 22
    // //         }
    // //     }).then ((res)=> console.log("f",res.data.RESULT_CODE))
    
    // fetch(
    //     "/stage/get"

    // ).then ((res)=> res.json())
    // );
    // //let resultCode = new Number (data.RESULT_CODE)
    // if (isLoading) return "Loading..";
    
    // if (error) return "An error has occured : " + error.message;
    // //changeGame(data.CURRENT_GAME)
    // //changeStage(data.CURRENT_STAGE)
    // console.log("YOURT",data.CURRENT_GAME)
    function renderStage(){
        switch (currentStage){
            case 2:
                return <Tutorial game={currentGame}/>
            case 3:
                return <Submit game={currentGame}/>
            case 4:
                return <Check game={currentGame}/>
            case 5:
                return <Show game={currentGame}/>
            case 6:
                return <Play game={currentGame}/>
            case 7:
                return <Result game={currentGame}/>
            case 8:
                return <Terminate /> 
            default:
                return <div/>
        }
    }
    return (
        
        <div>
            
            {stage.map ((stage, index) => {
            return <div id={index} style={{"display": "none"}} key={stage}>{stage}</div>
            })}

            {renderStage()}

        
            <p id="pressBack" style={{"display": "none"}}>뒤로가기를 눌러주세요 </p>
        </div>
    )
} 




export {Games};

