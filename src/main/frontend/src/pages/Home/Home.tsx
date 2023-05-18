import React, {useState} from 'react';
import HomeAxios from "./HomeAPI";

interface HomeProps{
    rootCall: (data: boolean, userName: string) => void;
    name: string;
}

function Home (props : HomeProps){




    const onClickLogout = (event : React.MouseEvent<HTMLButtonElement>) => {
        function checkName(name : string) {
            console.log("dsdsdsd"+name);

            // document.location.href = '/';
            props.rootCall (false, name);
        }

        HomeAxios ("/member/logout-confirm", checkName, props.name);
        // fetch('/onLogin')
        //     .then((res) => res.json())
        //     .then (data=>{setData(data); console.log("THIS : ",data.last)}, ()=>{console.log ("THAT : ",data)})
        //     .catch(err => console.log("fcc",err))
        //postLogin()


    }



    return (
        <div className="Home">
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">aaafffff</button>
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-blue-500 hover:bg-blue-700" type="submit" onClick={onClickLogout}>로그아웃</button>
        </div>
    );
}

export default Home;