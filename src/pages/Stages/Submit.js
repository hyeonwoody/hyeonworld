import React, { useEffect } from "react";

import Game0 from "../Games/0/Submit"

function Submit(props){
    
    function game(){
        console.log("포를", props.game)
        switch (props.game){
            case 0:
                return <Game0/>

            default:
                return <div></div>
        }
            
    }

    return (
        <div>
            {game()}
        </div>
        
    )
}
export default Submit;