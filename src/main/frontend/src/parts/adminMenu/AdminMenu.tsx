import React, {useEffect, useState} from "react";

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

function AdminMenu (){
    const [year, setYear] = useState(0);

    useEffect(()=>{
        const d = new Date();
        setYear(d.getFullYear());
    },[])


    const onClickScore = (event : React.MouseEvent<HTMLButtonElement>) => {
        console.log ("현재 점수");
    }

    return (
        <div className="AdminMenu">
            {Object.entries(AdminMenuList).map(([menuName, index]) =>{
                return <button  >{menuName}</button>;

                return null;
            })}
        </div>

    );
}
export default AdminMenu;