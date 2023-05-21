import axios from 'axios';
import {My} from '../../configuration/web/WebConfig';

const my = new My();
export function LogoutAxios(url: string, callback: (data: any) => void, logoutName : string) {
    axios({
        url: "/member/" + url,
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
        params: {
            logoutName : logoutName
        }
    }).then(function (response) {
            callback (response.data);
    });
};

export function HomeAxios(url: string, callback: (data: any) => void) {
    axios({
        url: "/home/" + url,
        method: 'get',
        baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
        withCredentials: true,
    }).then(function (response) {
        callback (response.data);
    });
};
