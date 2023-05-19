import React, {useEffect, useState} from 'react';
import {HomeAxios, LogoutAxios} from "./HomeAPI";

interface HomeProps{
    rootCall: (data: boolean, userName: string) => void;
    name: string;
}

interface Game{
    name: string;
    description: string;
}

function Home (props : HomeProps){

    const [gameList, setGameList] = useState <Game[]>([]);
    const [currentSlide, setCurrentSlide] = useState(0);

    const handleNextSlide = () => {
        setCurrentSlide((prevSlide) => (prevSlide + 1) % gameList.length);
    };

    const handlePrevSlide = () => {
        setCurrentSlide((prevSlide) =>
            prevSlide === 0 ? gameList.length - 1 : prevSlide - 1
        );
    };


    useEffect(()=>{

        function getGameList (data : Game[]){
            setGameList(data);
            console.log(gameList);
        }
        HomeAxios ("games/playable", getGameList);
    },[])


    const onClickLogout = (event : React.MouseEvent<HTMLButtonElement>) => {
        function checkName(name : string) {
            console.log("dsdsdsd"+name);

            // document.location.href = '/';
            props.rootCall (false, name);
        }

        LogoutAxios ("logout-confirm", checkName, props.name);
    }



    // @ts-ignore
    return (
        <div className="Home">
            <div className="flex flex-row py-4 px-0.5">
                <div className="flex-grow rounded-lg">
                    <div className="flex justify-end">
                        <button className="bg-red-600 mr-2  shadow-lg shadow- shadow-red-700 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row">Vvvaaaffaff</button>
                        <button className="bg-red-600 mr-2  shadow-lg shadow- shadow-red-700 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row" type="submit" onClick={onClickLogout}>현재 점수</button>
                        <button className="bg-red-500 mr-2  shadow-lg shadow- shadow-red-600 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row" type="submit" onClick={onClickLogout}>로그아웃</button>

                    </div>
                </div>
            </div>
            <ul className="p-2 space-y-1"/>
            <div className="flex mx-2 items-center justify-center rounded-xl group sm:flex space-x-2 space-y-0.1 bg-white bg-opacity-20 shadow-xl hover:rounded-2xl">

                {/*<ul className="cards">*/}
                {/*    {gameList.map((game: Game, i: number) => {*/}
                {/*        console.log(i);*/}

                {/*        return <li className={"card"+i%7} key={i}>*/}
                {/*            <h3 className="card-title">{game.name}</h3>*/}
                {/*            <div className="checkbox mb-3"></div>*/}
                {/*            {game.description}</li>*/}
                {/*    })}*/}
                {/*</ul>*/}
                <div className={"relative h-56 overflow-hidden rounded-lg md:h-96"}>
                    <div className={"carousel-wrapper"}>
                        {gameList.map((game: Game, i: number) => (
                            <div
                                key={i}
                                className={`carousel-item ${currentSlide === i ? 'active' : ''}`}
                            >
                                <h3 className="card-title">{game.name}</h3>
                                <div className="checkbox mb-3">{game.description}</div>

                            </div>
                        ))}
                    </div>
                    <button
                        type="button"
                        className="carousel-button carousel-prev"
                        onClick={handlePrevSlide}
                    >
                        Previous
                    </button>
                    <button
                        type="button"
                        className="carousel-button carousel-next"
                        onClick={handleNextSlide}
                    >
                        Next
                    </button>
                </div>
            </div>
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">aaafffff</button>
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-blue-500 hover:bg-blue-700" type="submit" onClick={onClickLogout}>로그아웃</button>
        </div>
    );
}

export default Home;