import {My} from "../../configuration/web/WebConfig";
import {type} from "@testing-library/user-event/dist/type";

export function StageAPI(url: string, getStage: (stage: string) => void) {
    const my = new My();

    console.log("fdfd");
    const eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+url);

    eventSource.addEventListener('connect', (e)=>{
        const {data: receivedConnectData} = e;
        console.log('connect event data11 : ',receivedConnectData);
        getStage(receivedConnectData);
    })


};