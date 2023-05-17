import axios from 'axios';
import {My} from '../../configuration/web/WebConfig';

let LoginAxios;
export default LoginAxios = function LoginAxios(url: string, loginName : string ,callback: (data: any) => void) {
    const my = new My();
    axios({
        url: url,
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            loginName : loginName
        }
    }).then(function (response) {
        callback(response.data);
    });
};