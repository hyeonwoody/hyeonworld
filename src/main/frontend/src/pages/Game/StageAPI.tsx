import {My} from "../../configuration/web/WebConfig";
import {type} from "@testing-library/user-event/dist/type";

export function StageAPI(url: string, getStage: (stage: number) => void) {
    const my = new My();
    let eventSource : EventSource;
    if (url != ""){
        eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+url);
        eventSource.addEventListener('connect', (e)=>{
            const {data: receivedConnectData} = e;
            console.log('connect event data101 : ',receivedConnectData);
            //getStage(receivedConnectData);
        });
        eventSource.addEventListener('currentGameStage', async (e)=>{
            const {data: receivedData} = e;
            const jsonObject = await JSON.parse(receivedData);
            console.log("음다"+jsonObject.cnt);
            getStage(jsonObject.cnt);
        });
    }

    function closeConnection(){
        console.log("Close Event Source");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};