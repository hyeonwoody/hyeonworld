import {My} from "../../../../configuration/web/WebConfig";
import {Special} from "../../../../configuration/special/SpecialConfig";
import axios from "axios";

export function RankingAPI(memberId : number) {
    const my = new My();
    const special = new Special();
    if (memberId == special.adminId){
        console.log("INIT ROUND");
        axios({
            url: "round/0",
            method: 'put',
            baseURL: `http://${my.ipAddress}:${my.backEndPort}`,
            withCredentials: true,
        });
    }


};