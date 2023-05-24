import {My} from "../../configuration/web/WebConfig";
import {type} from "@testing-library/user-event/dist/type";

export function WaitingAPI(getStage: (stage: number) => void) {
    const my = new My();
    let eventSource : EventSource;

    eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+"/api/game-stage/players");
    eventSource.addEventListener('connect', (e)=>{
        const {data: receivedConnectData} = e;
        console.log('connect event data101 : ',receivedConnectData);
        //getStage(receivedConnectData);
    });
    eventSource.addEventListener('currentGameStage', async (e)=>{
        const {data: receivedData} = e;
        const jsonObject = await JSON.parse(receivedData);

        getStage(jsonObject.currentStage);
    });

    function closeConnection(){
        console.log("Close Event Source");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};

export function StageAPI(getStage: (stage: number) => void) {
    const my = new My();
    let eventSource : EventSource;

        eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+"/api/game-stage/players");
        eventSource.addEventListener('connect', (e)=>{
            const {data: receivedConnectData} = e;
            console.log('connect event data101 : ',receivedConnectData);
            //getStage(receivedConnectData);
        });
        eventSource.addEventListener('currentGameStage', async (e)=>{
            const {data: receivedData} = e;
            const jsonObject = await JSON.parse(receivedData);
            console.log("음ㅁ다"+jsonObject.currentStage);
            getStage(jsonObject.currentStage);
        });

    function closeConnection(){
        console.log("Close Event Source");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};