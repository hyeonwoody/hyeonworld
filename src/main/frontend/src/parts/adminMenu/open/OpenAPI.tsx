import {My} from "../../../configuration/web/WebConfig";

import axios from "axios";

export function OpenGameAxios(game : number) {
    const my = new My();
    axios({
        url: "game/init/",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            game: game
        }
    }).then(function (response) {
    });
};