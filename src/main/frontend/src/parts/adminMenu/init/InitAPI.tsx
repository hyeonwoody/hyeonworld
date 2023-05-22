import {My} from "../../../configuration/web/WebConfig";

import axios from "axios";

export function InitAxios(group : number, persons : number) {
    const my = new My();
    axios({
        url: "/group/init",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            group : group,
            persons : persons,
        }
    }).then(function (response) {
    });
};