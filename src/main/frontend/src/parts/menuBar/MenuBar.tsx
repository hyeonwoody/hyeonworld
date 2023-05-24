import React, {useEffect, useState} from 'react';
import {LogoutAxios} from "../../pages/Home/HomeAPI";

interface MenuBarProps{
    moveBack: ()=> void;
    rootCall: (data: boolean, userName: string) => void;
    name: string;
}

function MenuBar (props : MenuBarProps){
    const [year, setYear] = useState(0);

    useEffect(()=>{
        const d = new Date();
        setYear(d.getFullYear());
    },[])

    const onClickLogout = (event : React.MouseEvent<HTMLButtonElement>) => {
        function checkName(name: string) {

            // document.location.href = '/';
            props.rootCall(false, name);
        }

        LogoutAxios(checkName, props.name);
    }

    const onClickScore = (event : React.MouseEvent<HTMLButtonElement>) => {
        console.log ("현재 점수");
    }

    return (
        <div className="MenuBar">
            <div className="flex flex-row py-4 px-0.5">
                <div className="flex-grow rounded-lg">
                    <div className="flex justify-end">
                        <button className="bg-red-600 mr-2  shadow-lg shadow- hover:shadow-red-700 hover:bg-red-700 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row" onClick={props.moveBack}>뒤로가기</button>
                        <button className="bg-red-600 mr-2  shadow-lg shadow- hover:shadow-red-700 hover:bg-red-700 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row" onClick={onClickScore}>현재점수</button>
                        <button className="bg-red-500 mr-2  shadow-lg shadow- hover:shadow-red-600 hover:bg-red-600 text-white cursor-pointer px-3 py-1 text-center justify-center items-center rounded-xl flex space-x-2 flex-row" onClick={onClickLogout}>로그아웃</button>

                    </div>
                </div>
            </div>
        </div>

    );
}
export default MenuBar;