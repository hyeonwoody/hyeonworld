import React, {useEffect, useState} from 'react';
import {DisplayGameAxios, CurrentGameAxios} from "./HomeAPI";
import './Home.css';


import MenuBar from "../../parts/menuBar/MenuBar";

import Game from "../Game/Game";
import AdminMenu from "../../parts/adminMenu/AdminMenu";
import {Special} from "../../configuration/special/SpecialConfig";

interface HomeProps{
    rootCall: (data : boolean, loginId: number, loginName :string) => void;
    id: number;
    name: string;
}

interface Game{
    id : number;
    name: string;
    description: string;
}

function Home (props : HomeProps){

    const [gameList, setGameList] = useState <Game[]>([]);
    const [enterGameId, setEnterGame] = useState <number> (-1);
    const [currentGameId, setCurrentGame] = useState <number> (-2);
    const special = new Special();



    useEffect(()=>{

        function getGameList (data : Game[]){
            setGameList(data);
        }

        DisplayGameAxios (getGameList);
    },[])


    const openGame = (id : number) => {
        setEnterGame(id);
    }

    const getCurrentGame = () => {
        CurrentGameAxios(setCurrentGame);
    }


    const onClickGame = (event : React.MouseEvent<HTMLLIElement>) => {
        const target = event.target as HTMLLIElement;
        const value : any = target.getAttribute("id");
        console.log("aaaaa"+currentGameId);
        console.log("Bbbbb"+value);
        openGame(value);
        getCurrentGame();
    }

    const onClickBack = () => {
        setEnterGame(-1);
    }


    // @ts-ignore
    return (
        <div className="Home">
            <MenuBar moveBack={onClickBack} rootCall={props.rootCall} loginId={props.id} loginName={props.name}/>
            <ul className="p-2 space-y-1"/>
            <div className="flex mx-2 items-center justify-center rounded-xl party sm:flex space-x-2 space-y-0.1 bg-white bg-opacity-20 shadow-xl hover:rounded-2xl">
                {enterGameId == currentGameId ? <Game id={currentGameId} stage={0}/> : <ul className="cards">
                    {gameList.map((game: Game, i: number) => {
                        return <li id={(game.id).toString()} className={"card"+i%7} key={i} onClick={onClickGame}>
                            <h3 className="card-title">{game.name}</h3>
                            <ul className="p-2 space-y-1"/>
                            {game.description}</li>
                    })}
                </ul>}

            </div>
            {special.adminId === props.id &&  <AdminMenu gameList={gameList}/>}
        </div>
    );
}

export default Home;