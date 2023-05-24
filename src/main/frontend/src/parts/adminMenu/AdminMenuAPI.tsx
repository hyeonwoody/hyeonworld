import {My} from "../../configuration/web/WebConfig";

import axios from "axios";

export function AdminMenuAxios(stage : number) {
    const my = new My();
    axios({
        url: "/api/game-stage/" + "admin",
        method: 'put',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            currentStage: stage
        }
    }).then(function (response) {
    });
};