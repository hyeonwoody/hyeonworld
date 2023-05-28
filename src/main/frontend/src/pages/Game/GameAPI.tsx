import {My} from "../../configuration/web/WebConfig";
import {type} from "@testing-library/user-event/dist/type";

export function WaitingAPI(getList: (stage: string[]) => void, memberId: number) {
    const my = new My();
    let eventSource : EventSource;
    console.log("WAAAAAA")
    eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+`/member/waiting-list?memberId=${memberId}`);
    eventSource.addEventListener('connect', (e)=>{
        const {data: receivedConnectData} = e;
        console.log('connect event data101 : ',receivedConnectData);
        //getStage(receivedConnectData);
    });
    eventSource.addEventListener('WaitingList', async (e)=>{
        console.log("WaitingLIST : LISTENER");
        const {data: receivedData} = e;
        const jsonObject = await JSON.parse(receivedData);
        getList(jsonObject.waitingList);
    });

    eventSource.addEventListener('additionalList', async (e)=>{
        console.log("WaitingLIST : LISTENER");
        const {data: receivedData} = e;
        const jsonObject = await JSON.parse(receivedData);
        getList(jsonObject.additionalList);
    });

    function closeConnection(){
        console.log("Close Event Source Waiting");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};

export function StageAPI(getStage: (stage: number) => void, memberId: number) {
    const my = new My();
    let eventSource : EventSource;

        eventSource = new EventSource('http://'+my.ipAddress+":"+my.backEndPort+`/api/game-stage?memberId=${memberId}`);
        eventSource.addEventListener('connect', (e)=>{
            const {data: receivedConnectData} = e;
            console.log('connect event data101 : ',receivedConnectData);
        });
        eventSource.addEventListener('currentGameStage', async (e)=>{
            console.log("currentGameStage : LISTENER");
            const {data: receivedData} = e;
            const jsonObject = await JSON.parse(receivedData);
            var jsonString = JSON.stringify(jsonObject, null, 2);
            getStage(jsonObject.gameStage);
        });

    function closeConnection(){
        console.log("Close Event Source STAGE");
        eventSource.close();
    }

    return {
        closeConnection,
    }
};