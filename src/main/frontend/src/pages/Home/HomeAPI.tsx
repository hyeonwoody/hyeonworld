import axios from 'axios';
import {My} from '../../configuration/web/WebConfig';

export default function HomeAxios(url: string, callback: (data: any) => void, logoutName : string) {
    const my = new My();
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