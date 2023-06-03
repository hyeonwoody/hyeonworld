import {My} from "../../../../configuration/web/WebConfig";
import axios from "axios";

export function ResultAPI(getNameList: (nameList: string[]) => void) {
    const my = new My();

    axios({
        url: "round/0",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
    }).then(function (response) {
        getNameList(response.data);
    });
};