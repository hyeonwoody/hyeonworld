import React, {useEffect, useState} from "react";

import InitModal from "./init/InitModal";
import OpenModal from "./open/OpenModal";
import {type} from "@testing-library/user-event/dist/type";

interface Game{
    name: string;
    description: string;
}

interface AdminMenuProps{
    gameList: Game[];
}

export const AdminMenuList = {
    Init: 0,
    Open: 1,
    Tutorial: 2,
    Submit: 3,
    Check: 4,
    Show: 5,
    Play: 6,
    Result: 7,
    Ranking: 8,
    Done: 9,
};

function AdminMenu (props: AdminMenuProps){

    const [initModal, setInit] = useState<boolean>(false);
    const [openModal, setOpen] = useState<boolean>(false);

    useEffect(()=>{
    },[])

    const onInit = () => {
        setInit(!initModal);
    }
    const onOpen = () => {
        console.log(props.gameList.length);
        setOpen(!openModal);
    }




    const onClickButton = (event : React.MouseEvent<HTMLButtonElement>) => {
        const target = event.target as HTMLLIElement;
        const value : any = target.getAttribute("id");
        const parsedValue : number = parseInt(value);

        switch (parsedValue) {
            case AdminMenuList["Init"]:
                onInit();
                break;
            case AdminMenuList["Open"]:
                onOpen();
                break;
            default:
                console.log("ㅁㄹ");
        }

    }

    return (
        <div className="AdminMenu">
            <div className={"grid grid-cols-5"}>
                {initModal && <InitModal onInit={onInit}/>}
                {openModal && <OpenModal onOpen={onOpen} gameList={props.gameList}/>}
            {Object.entries(AdminMenuList).map(([menuName, index]) =>{
                return <button
                        type={"button"}
                        className={"bg-green-200 rounded-2xl mx-1 my-1 text-gray-900 hover:bg-gray-100" }
                        data-modal-target={"init-modal"}
                        data-modal-toggle={"init-modal"}
                        id={index.toString()}
                        key={index}
                        onClick={onClickButton}>
                        {menuName}
                        </button>;
            })}
            </div>
        </div>

    );
}
export default AdminMenu;