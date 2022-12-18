import React from "react";
import { useQuery } from "react-query";
function Games(number){
    // /**
    //  * standard
    //  * @returns 
    //  */
    // const usePosts = () => useQuery(['posts', 'list'], fetchPosts)

    // const usePost = (id)=>
    //     useQuery(['posts', 'detail', id], ()=> fetchPost(id))
    // const useReactQuerySubscription = () =>{
    //     React.useEffect(()=>{
    //         const WebSocket = new WebSocket ('wss://echowebsocket.org/')
    //         WebSocket.onopen=()=>{
    //             console.log('connected')
    //         }

    //         WebSocket.onmessage = (event) =>{ //event based subsription
    //             const data = JSON.parse (event.data)
    //             const queryKey = [...data.entity, data.id].filter(Boolean)
    //             queryClient.invalidateQuery(queryKey)
    //         }
            
    //         WebSocket.onmessage = (event) =>{ //partial - update
    //             const data = JSON.parse (event.data)
    //             queryClient.setQueriesData (data.entity, (oldData)=> {
    //                 const update = (entity) =>
    //                     entity.id === data.id ? {...entity, ...data.payload} : entity
    //                 return Array.isArray (oldData) ? oldData.map (update) : update(oldData)
    //             })
    //         }
    //         return ()=>{
    //             WebSocket.close()
    //         }
    //     },[])
    // }
    var currentGame
    const {isLoading, error,data,isFetching} = useQuery ('repoData', ()=>
    // axios.get (
    //     "https://api.github.com/repos/tannerlinsley/react-query"
    // ).then ((res)=>res.json())
    
    // axios.get('/stage', null, {
    //         params: {
    //             NAME : 22
    //         }
    //     }).then ((res)=> console.log("f",res.data.RESULT_CODE))
    
    fetch(
        "/stage/get"

    ).then ((res)=> res.json())
    );
    //let resultCode = new Number (data.RESULT_CODE)
    if (isLoading) return "Loading..";
    
    if (error) return "An error has occured : " + error.message;
    currentGame = data.CURRENT_GAME
    
    console.log("YOURT",data.CURRENT_GAME)

    //const socket = io('http://172.30.1.14:3004')

    return (
        <div>
            <strong> {data.RESULT_CODE}</strong>
            <div> {isFetching ? "Updating.." : ""}</div>
            
        </div>
    )
} 




export default Games;