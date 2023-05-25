import {My} from "../../configuration/web/WebConfig";
import {type} from "@testing-library/user-event/dist/type";

export function WaitingAPI(getList: (stage: string[]) => void, memberId: number) {
    const my = new My();
    let eventSource : EventSource;

    eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+`/member/waiting-list?param=${memberId}`);
    eventSource.addEventListener('connect', (e)=>{
        const {data: receivedConnectData} = e;
        console.log('connect event data101 : ',receivedConnectData);
        //getStage(receivedConnectData);
    });
    eventSource.addEventListener('gameEnterWaitingList', async (e)=>{
        const {data: receivedData} = e;
        const jsonObject = await JSON.parse(receivedData);

        getList(jsonObject.currentStage);
    });

    function closeConnection(){
        console.log("Close Event Source");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};

export function GameAPI(getStage: (stage: number) => void) {
    const my = new My();
    let eventSource : EventSource;

        eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+"/api/game-stage");
        eventSource.addEventListener('connect', (e)=>{
            const {data: receivedConnectData} = e;
            console.log('connect event data101 : ',receivedConnectData);
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