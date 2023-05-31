import {My} from "../../../../configuration/web/WebConfig";
import axios from "axios"

export function SubmitAPI(onSend: (val: boolean) => void, input: string[], inputFalse: number) {
    const my = new My();

    axios({
        url: "api/game-stage/" + "init",
        method: 'post',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            input: input,
            inputFalse : inputFalse,
        }
    }).then(function (response) {
        onSend(response.data);
    });
};