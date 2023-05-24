import {My} from "../../../configuration/web/WebConfig";

import axios from "axios";

export function InitAxios(partyType : number, persons : number) {
    const my = new My();
    axios({
        url: "party/init/",
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            partyType : partyType,
            persons : persons,
        }
    }).then(function (response) {
    });
};