import React, {ChangeEvent, useState} from 'react';

import {InitAxios} from "./InitAPI"

export const Groups: {[key: number] : string} = {
    0:"외가",
    1:"친가",
    2:"가족",
}

const InitModal = ({onInit}: any) => {

    const [persons, setPerson] = useState<number>(0);
    const [group, setGroup] = useState<number> (0);

    const familySlider = document.getElementById("family-slider");
    const personsSlider = document.getElementById('persons-slider');

    if (familySlider){
        familySlider.addEventListener('input', function(event) {
            //const value = event.target.value;
            console.log("aaaaaa");
            // Do something with the value, such as updating the UI or making API calls
        });
    }
    if (personsSlider){
        personsSlider.addEventListener('input', function(event) {
            const value = (event.target as HTMLInputElement).value;
            console.log("ffdsf");
            console.log(value);
            // Do something with the value, such as updating the UI or making API calls
        });
    }

    const handlePersons = (event : ChangeEvent<HTMLInputElement>) => {
        setPerson(Number(event.target.value));
        console.log(persons);
        console.log(Groups[0]);
    };

    const handleGroups = (event : ChangeEvent<HTMLInputElement>) => {
        setGroup(Number(event.target.value));
    };

    const commitInit = (event : React.MouseEvent<HTMLButtonElement>) => {
        InitAxios(group, persons)
    }


    return (
        <div className={"h-screen w-full fixed left-0 top-0 flex justify-center bg-black bg-opacity-70"}>
            <div className={"bg-white fixed bottom-1/3 rounded-2xl w-10/12 h-2/5"}>
                <div className={"border-b px-4 flex justify-between items-center"}>
                    <h3 className={"font-extrabold"}>Init</h3>
                </div>

                <label htmlFor="family-slider"
                       className="block mb-2 text-sm font-medium text-gray-900">
                    {Object.values(Groups).map((group) =>{
                        return <p>{group}</p>
                    })}
                  </label>
                <input className={"text-center"} type={"text"} value={Groups[group]} onChange={handleGroups}></input>
                <input id="default-range" type="range" min="0" max={Object.keys(Groups).length} step={"1"} onChange={handleGroups} defaultValue={0} value={group}
                       className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700"
                />

                <label htmlFor="persons-slider"
                       className="block mb-2 text-sm font-medium text-gray-900">인원</label>
                <input className={"text-center"} type={"text"} value={persons} onChange={handlePersons}></input>
                <input id="default-range" type="range" min="1" max="40" step={"1"} onChange={handlePersons} defaultValue={1} value={persons}
                       className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700"
                />

                <div className={"border-b px-4 py-2 flex items-center"}/>
                <div className={"flex justify-center items-center w-100 py-2 text-gray-500"}>
                    <button onClick={commitInit} className={"bg-red-600 hover:bg-red-700 rounded text-white mx-2"}>초기화</button>
                    <button onClick={onInit} className={"bg-gray-600 hover:bg-gray-700 rounded text-white"}>취소</button>
                </div>
            </div>
        </div>
    )
}

export default InitModal;