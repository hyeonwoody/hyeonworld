import axios from 'axios';
import {My} from '../../configuration/web/WebConfig';

export default function LoginAxios(loginName : string ,callback: (data: any) => void) {
    const my = new My();
    axios({
        url: "member/" + "login-confirm",
        method: 'put',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            loginName : loginName
        }
    }).then(function (response) {
        callback(response.data);
    });
};