import React, {useEffect, useState} from "react";

import InitModal from "./init/InitModal"

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

function ModalInit(props: { onOpenAlert: void }) {
    return null;
}

function AdminMenu (){
    const [year, setYear] = useState(0);

    const [init, setInit] = useState<boolean>(false);

    useEffect(()=>{
        const d = new Date();
        setYear(d.getFullYear());
    },[])

    const onInit = () => {
        console.log(init);
        setInit(!init);
        console.log(init);
    }




    const onClickButton = (event : React.MouseEvent<HTMLButtonElement>) => {
        const target = event.target as HTMLLIElement;
        const value : any = target.getAttribute("id");
        const parsedValue : number = parseInt(value);
        console.log(parsedValue);
        switch (parsedValue) {
            case AdminMenuList["Init"]:
                onInit();
        }

    }

    return (
        <div className="AdminMenu">
            <div className={"grid grid-cols-5"}>
                {init && <InitModal onInit={onInit}/>}
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

                return null;
            })}
            </div>
        </div>

    );
}
export default AdminMenu;