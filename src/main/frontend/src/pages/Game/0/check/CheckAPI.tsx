import {My} from "../../../../configuration/web/WebConfig";
import axios from "axios"
import {data} from "autoprefixer";
import React from "react";

export function CheckAPI(getPlayer: (name : string, input : string[], inputFalse : number) => void, memberId: number) {
    const my = new My();


    axios({
        url: "submission/0",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            memberId: memberId,
        }
    }).then(function (response) {
        const dataList = response.data;

        // const mappedList = dataList.map ((item : any) => ({
        //     inputFalse : item.number,
        //     text: item.text,
        // }));
        console.log(dataList);


        Object.entries(dataList).map(([name, arr])=>{
            console.log(name);
            // @ts-ignore
            const map = new Map(Object.entries(arr));

            const text : unknown = (map.get('text'));

            // @ts-ignore
            const input = text.split(';');

            const inputFalse = map.get('number');

            // @ts-ignore
            getPlayer(name, input, inputFalse);
        });

        //console.log(typeof (dataList));
    });
};