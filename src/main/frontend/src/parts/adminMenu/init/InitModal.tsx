import React, {useState} from 'react';

const InitModal = ({onInit}: any) => {

    const [persons, setPerson] = useState(0);

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




    return (
        <div className={"h-screen w-full fixed left-0 top-0 flex justify-center bg-black bg-opacity-70"}>
            <div className={"bg-white rounded-2xl w-10/12 h-2/3"}>
                <div className={"border-b px-4 flex justify-between items-center"}>
                    <h3 className={"font-extrabold"}>Init</h3>
                </div>

                <label htmlFor="family-slider"
                       className="block mb-2 text-sm font-medium text-gray-900">외가 : 친가</label>
                <input id="default-range" type="range" min="1" max="2" value={1}
                       className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700"
                />

                <label htmlFor="persons-slider"
                       className="block mb-2 text-sm font-medium text-gray-900">인원</label>
                <input id="default-range" type="range" min="1" max="100" value={persons}
                       className="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700"
                />

                <div className={"border-b px-4 py-2 flex justify-between items-center"}/>
                <div className={"flex justify-center items-center w-100 p-3 text-gray-500"}>
                    <button onClick={onInit} className={"bg-gray-600 hover:bg-gray-700 rounded text-white"}>확인</button>
                </div>
            </div>
        </div>
    )
}

export default InitModal;