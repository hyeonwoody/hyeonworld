import React, {useState} from 'react';

interface HomeProps{
    isLogin : boolean;
}

export default function Home (props : HomeProps) {
    const [isLogin, setLogin] = useState (props.isLogin);
    console.log ("Hozzme");
    return (
        <div className="Home">
            <button className="py-2 px-4 rounded-lg shadow-md text-white bg-green-500 hover:bg-green-700">aaafffff</button>
        </div>
    );
}