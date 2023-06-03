import {My} from "../../../../configuration/web/WebConfig";
import axios from "axios";
import {type} from "@testing-library/user-event/dist/type";

export function ResultAPI(getNameList: (nameList: string[]) => void) {
    const my = new My();

    axios({
        url: "round/0",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
    }).then(function (response) {
        //console.log("aa"+response.data);
        //console.log(response.data.keys()); //Array Iterator()

        //const stringArray : string[] = Array.from (Object.values(response.data));

        getNameList(response.data);
    });
};